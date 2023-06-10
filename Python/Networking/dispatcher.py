import socket , sys , stomp , time 

from multiprocess import Process

from interface import Service 




def proc_fun(conn,proxy , mess):
    
    request = mess.split('-')[0]
    
    if request == "deposita" :
        id =mess.split('-')[1]
        result = proxy.deposita(id)
    else:
        result = proxy.preleva()
    
    conn.send('/send/response',result)






class ServiceProxy(Service):
    
    def __init__(self,port):
        self.port= port
        self.ip='localhost'
        self.buffer_size=1024
        
        
    def preleva(self):
        
        s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        s.connect((self.ip,self.port))
        
        
        message= "preleva"
        s.send(message.encode("utf-8"))
        
        data = s.recv(self.buffer_size)
        
        s.close()
        
        
        return data


    def deposita(self,id):
        
        s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        s.connect((self.ip,self.port))
        
        
        message= "deposita-"+str(id)
        s.send(message.encode("utf-8"))
        
        data = s.recv(self.buffer_size)
        
        s.close()
        
        
        return data













class MyListener(stomp.ConnectionListener):
    
    def __init__(self,conn , port):
        self.port=port
        self.conn= conn
        
    def on_message(self, frame):
        print('[DISPATCCHER] Request received : "%s"'%frame.body)
        
        proxy =ServiceProxy(int(self.port))
        
        p= Process(target=proc_fun, args=(conn, proxy, frame.body))
        p.start()








if __name__=="__main__":
    
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Pleasse , specify PORT arg")
        
        #Create connection
    conn = stomp.Connection([('127.0.0.1',61613)])
    
    #Set the listener
    conn.set_listener('',MyListener(conn,PORT))
    
    # Connect and subscribe to the queue 'response'
    
    conn.connect(wait =True)
    conn.subscribe(destination='/queue/response',id=1,ack='auto')
    
    print("[DISPATCHER] Waiting for request ...")
    
    #keep the listener active 
    
    while True:
        time.sleep(60)