

import sys , stomp , time , multiprocess

def proc_fun(conn, proxy , mess):
    
    request = mess.split('-')[0]
    
    if 'deposita' in request:
        
        id=mess.split('-')[1]
        result= proxy.deposita(id)
    else :
        result = proxy.preleva()
    
    conn.send('/queue/response',result)
    
    
    
    
    
class ServiceProxy(Service):
    
    def __init__(self ,port ):
        self.port = port
        self.ip ='localhost'
        self.buffer_size= 1024
        
        
        
        
    def preleva(self):
        #
    
    
    

class MyListener(stomp.ConnectionListener):
    
    def __init__(self, conn , port) :
        self.conn= conn
        self.port = port
    
    def on_message(self, frame):
        print("[Dispatcher] richiesta ricevuta %s"%frame.body)
        
        proxy=ServiceProxy(int(self.port))
    








if __name__=="__main__":
    
    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Please , add port arg ")
        
        
    conn = stomp.Connection([('localhost',61613)])
    
    conn.set_listener('',MyListener(conn,PORT))
    
    conn.connect(wait=True)
    
    conn.subscribe(destination='/queue/response',id=1,ack='auto')
        
