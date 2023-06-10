from interface import Service
import socket, sys
import multiprocess as mp




# Process function
def proc_fun(c, service):

    # Receive the request
    data = c.recv(1024)

    # Chek the tyope of request and invoke the proper Service method
    if str(data.decode()) == "preleva" :

        result = service.preleva()

    else:

        id = str(data.decode()).split('-')[1]
        result = service.deposita(id)
    
    # Send the response
    c.send(str(result).encode())

    # Close connection
    c.close()









class ServiceSkeleton(Service):
    
    def __init__(self,port,queue):
        self.port=port
        self.queue=queue
        
    def depoista(self, id):
        pass
    
    def preleva(self):
        pass
    
    def run_skeleton(self):
        
        host='localhost'
        
        s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        s.bind((host, self.port))
        
        s.listen(5)
        print("Socket is listening ")
        
        while True:
            
            
            client, addr= s.accept()
            
            p=mp.Process(target=proc_fun,args=(client,self))
            p.start()
            
        s.close()




class ServiceImpl(ServiceSkeleton):
    
    def deposita(self, data):

        self.queue.put(data)
        print("[SERVER-IMPL] Depositato", data)
        
        return "deposited"
    
    def preleva(self):

        result = self.queue.get()
        print("[SERVER-IMPL] Prelevato", result)
        
        return result







if __name__=="__main__":
    
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Please, specify PORT arg")
    
    print("Server running ... ")
    
    q = mp.Queue(5)
    
    serviceImpl = ServiceImpl(int(PORT),q)
    serviceImpl.run_skeleton()