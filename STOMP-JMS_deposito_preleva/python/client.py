import stomp ,random  ,time 




class MyListener(stomp.ConnectionListener):
    
    def on_message(self, frame):

        print('[CLIENT] Received respoense : "%s"' %frame.body)







if __name__=="__main__":
    
    connection = stomp.Connection([('127.0.0.1',61613)],auto_content_length=False)
    
    connection.set_listener('',MyListener())
    
    connection.connect(wait=True)
    connection.subscribe(destination='/queue/response',id=1,ack='auto')
    
    
    
    for i in range(20):
        
        if ((i%2)==0):
            
            request ="deposita"
            id = random.randint(1,100)
            MSG = request+"-"+str(id)
            
            
        else:
            
            MSG = "preleva"
            
        connection.send('/queue/request',MSG)
        
        print("[CLIENT] Request : ",MSG)
        
    
    while(True):
        
        time.sleep(60)
    
    connection.disconnect()
        