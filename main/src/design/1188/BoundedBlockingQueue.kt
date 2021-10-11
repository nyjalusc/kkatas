package design.`1188`

import java.util.concurrent.locks.ReentrantLock


/**
 * Semaphores: Used to manage and restrict the use of resources. Every thread can use semaphore as a permit to do something.
 * Methods: acquire() and release() Restrict numbers of concurrent access
 *
 * Reentrant Locks: Used to restrict access of shared resource, allow one at a time. Methods: lock() and unlock().
 * Similar to synchronized block but it is more flexible and requires explicit usage. if constructor set to true, it will
 * use fair policy, ie. out of the waiting threads, the one that waited the longest will get the lock. use tryLock() -> boolean
 * if you do not want the thread to wait if lock is not acquired
 *
 * Reentrant locks allow only one thread at a time. ReadWrite (called ReentrantReadWriteLock) locks are more efficient if we want to allow multiple threads
 * to read but only one thread to write into the shared resource.NOTE when using ReadWrite locks, concurrent read and write not allowed,
 * either N threads can acquire readlock and view the state OR a single writer will be using writelock to update the resource
 *
 * Conditions: used for synchronizing operations between multiple threads. You can wait for a certain condition to be met,
 * and the resume work once met. Conditions are created on top of Reentrant locks
 *
 * wait(), notify() and notifyAll() can be called only from synchronized blocks. ie. not the case with Conditions
 * Reentrant locks can be used in multiple places simultaneously, ie. in 2 separate functions, it just means that once lock is acquired another thread cannot enter the same function, but it can go use another function if no one is executing the critical section
 * https://www.youtube.com/watch?v=N0mMm5PF5Ow
 */
class BoundedBlockingQueue(capacity: Int) {
    private val lock = ReentrantLock()
    private val full = lock.newCondition()
    private val empty = lock.newCondition()
    private val queue= ArrayDeque<Int>(capacity)
    private val limit: Int = capacity

    @Throws(InterruptedException::class)
    fun enqueue(element: Int) =
        try {
            lock.lock()
            // This is to avoid spurious wake-ups, sometimes threads can wake up even without getting a wakeup signal
            // The while helps to catch such cases.
            while (limit == queue.size) {
                // Waiting for the condition to break
                full.await()
            }
            queue.addFirst(element)
            empty.signal() // Signal any waiting threads to start consuming
        } finally {
            lock.unlock()
        }

    @Throws(InterruptedException::class)
    fun dequeue(): Int = try {
        lock.lock()
        while (queue.isEmpty()) {
            empty.await()
        }
        val res = queue.removeLast()
        full.signal()
        res
    } finally {
        lock.unlock()
    }

    @Throws(InterruptedException::class)
    fun size(): Int = try {
        lock.lock()
        queue.size
    } finally {
        lock.unlock()
    }
}