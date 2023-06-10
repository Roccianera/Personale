import threading , time  , logging
from random import randint




CONSUMER = 'Consumer'
PRODUCER = 'Producer'
N_CONSUMERS = 10
N_PRODUCERS = 10
QUEUE_SIZE = 5


logging.basicConfig(level=logging.DEBUG, format='[%(threadName)-0s] %(message)s')








def get_an_available_item(queue):
    return queue.pop(0)


def make_an_item_available(queue):
    item = randint(0, 100)
    queue.append(item)

    return item






def prodThread(queue, mutex, full, empty):
    
    
    
    empty.acquire()
    
    with mutex:
        
        
        
        
        
        
        
        
    
    full.realese()
    
    
    
        
    
    
    
    
    









def main():
    
    
    queue = []
    producers =[]
    consumers =[]



    mutex = threading.Semaphore()
    empty = threading.Semaphore(QUEUE_SIZE)
    full = threading.Semaphore(0)
    
    
    
        
    for i in range(N_PRODUCERS):
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
if __name__=="__main__":
    main()





