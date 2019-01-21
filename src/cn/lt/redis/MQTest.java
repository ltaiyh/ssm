package cn.lt.redis;

import java.util.Random;

import redis.clients.jedis.Jedis;

/**
 * 使用redis中的list实现消息队列
 * @author lt
 * @Date 2018年1月2日 下午4:45:10
 */
public class MQTest {
	// 演示消息队列的操作
	public static void main(String[] args) {

		new Producer("蛋蛋").start();
		new Producer("建国").start();

		new Consumer().start();
		new Consumer().start();
	}
}

// 生产者
class Producer extends Thread {
	private String name;
	private int count = 1;// 生产消息的个数

	public Producer(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		Jedis jedis = new Jedis("localhost");
		// 循环着 让生产者随机睡眠一段时间，然后生产一条消息放入到消息队列
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(200 + random.nextInt(1000));// 200-1200
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 生产消息 -- 用字符串表示
			String message = name + "生产的第" + count + "个消息";
			count++;

			// 把消息放入消息队列
			jedis.rpush("mq", message);
		}
	}
}

// 消费者
class Consumer extends Thread {
	@Override
	public void run() {
		// 循环着，先休眠一段时间，再从消息队列中取出消息处理
		Jedis jedis = new Jedis("localhost");
		Random random = new Random();
		while (true) {
			try {
				Thread.sleep(200 + random.nextInt(1000));// 200-1200
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// 从消息队列中取出消息
			String message = jedis.lpop("mq");

			// 处理消息--简单的输出一下
			if (message != null) {
				System.out.println(message);
			}

		}
	}
}
