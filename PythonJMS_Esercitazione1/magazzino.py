
import stomp , time , random , multiprocessing 




def proc_fun(connection ,queue , message):
    
    MSG =""
    print("bug")
    if "deposita"  in message :
        
        data = int(message.split('-')[1])
        
        
        queue.put(data)
        MSG = "Deposito " + message +" A buon fine "
        
    elif "preleva" in message :
        
        data = queue.get()
        MSG = "Prelievo " +str(data) +" A buon fine "
        
    connection.send("/queue/response",MSG)
            



class MyListener(stomp.ConnectionListener):
    
    def __init__(self,queue, connection) -> None:

        self.connection= connection
        self.queue= queue
        
    
    
    def on_message(self, frame):

        print(f"[SERVER] Messaggio arrivato {frame.body}")
        type(frame.body)
        
        process = multiprocessing.Process(target=proc_fun,args=(self.connection,self.queue,frame.body))
        process.start()
        
        
        
        




if __name__=="__main__":
    
    
    
    conn = stomp.Connection([('127.0.0.1',61613)])
    
    queue= multiprocessing.Queue(10)
    
    
    conn.set_listener('',MyListener(queue,conn))
    
    conn.connect(wait=True)
    conn.subscribe('/queue/request' ,id=1 ,  ack='auto')
    
    
    while True :
        
        time.sleep(60)    

    
    
    
    
    
    
    