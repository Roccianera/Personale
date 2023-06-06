import logging
import threading
import time
from random import randint



CONSUMER ='Consumer'
PRODUCER='Producer'
N_CONSUMERS=10
N_PRODUCERS=10
QUEUE_SIZE = 5





logging.basicConfig(level=logging.DEBUG, format='[%(threadName)-0s] %(message)s'); #0s lo spazio che devi dare 



class consumerThread(threading.Thread):
    
    def __init__(self,producer_cv, consumer_cv, queue, name):
        threading.Thread.__init__(self,name)
        
        self.producer_cv=producer_cv
        self.consumer_cv=consumer_cv
        self.queue=queue
    def run(self):
        
        logging.debug('Started')   #=> [Consumer1] Started 
        ##posso consumare ?
        
        with self.consumer_cv:
            
            while(len(queue)==0):
                logging.debug('Devo aspettare la consumazione ')
                self.consumer_cv.wait()
            
                
            




def main():
    #Creiamo la coda
    
    queue = {}
    # creiamo le var. per la synch  , vogliamo usare le var . cond 
    cv_lock =threading.Lock()
    producer_cv = threading.Condition(lock=cv_lock)
    consumer_cv = threading.Condition(lock=cv_lock)
    
    consumers = []
    producers= []
    ##generare i consumatori
    
    for  i in range(N_CONSUMERS):
        name = CONSUMER +str(i)
    
        ct=consumerThread(producer_cv,consumer_cv,queue,name)
        ct.start()
        
        consumers.append(ct)
        
        
     
    ## generiamo i prod 
    
    
    
    
    
    
    
    
    pass




if __name__ =="__main__":
    main()