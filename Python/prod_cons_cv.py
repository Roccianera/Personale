### ESERCIZIO PROD_CONS  con variabili condition


import logging  , threading, time
from random import randint




CONSUMER ='Consumer'
PRODUCER ='Producer'
N_CONSUMERS = 10 
N_PRODUCERS = 10
QUEUE_SIZE = 5


logging.basicConfig(level=logging.DEBUG, format='[%(threadName)-0s] %(message)s')


def an_item_is_available(queue):
    return not(len(queue)==0)


def a_space_is_available(queue):
    return not (len(queue)== QUEUE_SIZE)

def get_an_available_item(queue):
    return queue.pop(0)

def make_an_item_available(queue):
    item=  randint(0,100)
    queue.append(item)
    return item




class consumerThread(threading.Thread):
    
    def __init__(self , producer_cv,consumer_cv,queue,name):
        
        threading.Thread.__init__(self,name=name)
        self.producer_cv=producer_cv
        self.consumer_cv=consumer_cv
        self.queue=queue
        
        
    def run(self):
        logging.debug('\t\t\tStarted')
        
        
        ## con questa opeazione faccio il lock in automatico 
        
        with self.consumer_cv:
            logging.debug('\t\t\tObtained lock')
            
            while not an_item_is_available(self.queue):
                logging.debug('\t\t\t Waiting')
                self.consumer_cv.wait()
            
            time.sleep(1.0)
            item =get_an_available_item(self.queue)
            logging.debug('\t\t\tItem : %r',item)
            
            logging.debug('\t\t\tNotify')
            self.producer_cv.notify()
            
        
        logging.debug('\t\t\tRealesed lock')
        
        
        
        
def produce_one_item(producer_cv,consumer_cv,queue):
    logging.debug('Started')
    
    with producer_cv:
        logging.debug('Obtained lock')
        
        while not a_space_is_available(queue):
            logging.debug('Waitinf')
            producer_cv.wait()
        
        time.sleep(1.0)
        item = make_an_item_available(queue)
        logging.debug('Item:%r',item)
        
        
        logging.debug('Notify')
        consumer_cv.notify()
        
    logging.debug('Released lock')
    
    
    
    
def main():
    
    queue =[]
    
    
    cv_lock= threading.Lock()
    producer_cv = threading.Condition(lock=cv_lock)
    consumer_cv = threading.Condition(lock=cv_lock)
    
    consumers = []
    producers = []
    
    
    
    for i in range(N_CONSUMERS):
        
        name=CONSUMER+str(i)
        
        ct= consumerThread(producer_cv,consumer_cv,queue,name)
        ct.start()
        
        consumers.append(ct)
        
    
    for i in range(N_PRODUCERS):
        
        pt = threading.Thread(
            target=produce_one_item,
            name=PRODUCER+str(i),
            args=(producer_cv,consumer_cv,queue)
        )
        
        pt.start()
        
        producers.append(pt)
        
        
    # waiting consumers termination
    for i in range (N_CONSUMERS):

        consumers[i].join()


    # waiting producers termination
    for i in range (N_PRODUCERS):

        producers[i].join()



if __name__ == '__main__':
    main()

