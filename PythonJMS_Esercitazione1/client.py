
import stomp , time , random 





class MyListener(stomp.ConnectionListener):
    
    
    
    def on_message(self, frame):
        print("Bug")

        print(f"[CLIENT] Messaggio arrivato {frame.body}")




if __name__=="__main__":
    
    
    
    conn = stomp.Connection([('127.0.0.1',61613)])
    
    
    conn.connect(wait=True)
    
    conn.set_listener('',MyListener())
    
    conn.subscribe('/queue/response' , id =1 ,  ack='auto')
    
        
    
    for i in range(10):
        
       
        operation =""
        
        if(random.randint(1,100)%2==0):
            
            operation = "deposita"
        else:
            operation ="preleva"

    
    
        MSG =  operation+"-"+str(random.randint(0,1200))
        
        
        conn.send('/queue/request',MSG)
        print("[client]  messaggio inivato '%s"%MSG)
    
    
    while True :
        
        time.sleep(60)    

    
    
    
    
    
    
    