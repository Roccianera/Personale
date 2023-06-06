import stomp ,random , time 


class MyListener(stomp.ConnectionListener):
    
    def on_message(self, frame):
    
        print('[Client] receved resp : %s ',frame.body)




if __name__=="__main__":
    conn = stomp.Connection([('localhost',61613)])
    
    conn.set_listener('',MyListener())
    
    conn.connect(wait=True)
    
    conn.subscribe(destination='/queue/response',id=1,ack='auto')
    
    for  i in range(10):
        
        if(i%2==0):
            request ='deposita'
            id= random.randint(1,100)
            MSG = request +'-'+str(id)
        else :
            
            MSG='preleva'
        
        conn.send('/queue/response')
        print("[CLIENT] Request : ",MSG)
    

        
    
    ####......fai while true con un sleep (60)
    
    
    while True:
        time.sleep(60)
    
    
    
    
    
    
    
    #### se impostiamo un numero infinto di interazioni 
    #### conn.disconnet()
    
    
    
    
    
    
    
    
    
    
    
    
    
                