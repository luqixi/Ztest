package com.linkstec.kafka;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaConsumerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(KafkaConsumerTest.class);
	
	private ConsumerConnector consumer;
	private ExecutorService executor;
	
	private void start(){
		System.out.println("kaishi");
		consumer = kafka.consumer.Consumer.createJavaConsumerConnector(createConsumerConfig());
		System.out.println("realtimeHQ");
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
//		String topic = "lmspStormCommand";
//		String topic = "realtimeHQ";
		String topic = "test";
		Integer a_numberThreads = 1;
		topicCountMap.put(topic, a_numberThreads);
		Map<String, List<KafkaStream<byte[], byte[]>>> consumerMap = consumer.createMessageStreams(topicCountMap);
		List<KafkaStream<byte[], byte[]>> streams = consumerMap.get(topic);
		executor = Executors.newFixedThreadPool(a_numberThreads);
		for(final KafkaStream<byte[], byte[]> stream : streams){
			executor.submit(new ConsumerThread(stream));
		}
		
	}

	/*private void stop(){
		if(consumer != null) consumer.shutdown();
		if(executor != null) executor.shutdown();
		try{
			if(!executor.awaitTermination(5000, TimeUnit.MILLISECONDS)){
				logger.debug("Timed out waiting for consumer threads to shut down, exiting uncleanly");
			}
		}catch(Exception  e){
			logger.debug("Interrupted during shutdown, exiting uncleanly");
		}
	}*/
	
	private ConsumerConfig createConsumerConfig(){
		Properties props = new Properties();
		props.put("zookeeper.connect", "localhost:2181");
//		props.put("zookeeper.connect", "192.168.56.175:2181,192.168.70.188:2181,192.168.70.189:2181");
		props.put("group.id", "htsmotTest");
		props.put("auto.commit.enable", "true");
		props.put("auto.commit.interval.ms", "60000");
		props.put("rebalance.max.retries", "4");
		props.put("auto.offset.reset", "largest");
		props.put("zookeeper.connection.timeout.ms", "15000");
		props.put("zookeeper.session.timeout.ms", "15000");
		
		logger.info("props");
		return new ConsumerConfig(props);
	}
	
	
	class ConsumerThread implements Runnable {
		
		private KafkaStream<byte[], byte[]> m_stream;
		
		private int index = 0;
		private int errorIndex = 0;
		
		public ConsumerThread(KafkaStream<byte[], byte[]> a_stream){
			m_stream = a_stream;
		}
		
		@Override
		public void run() {
			
			ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
			while(it.hasNext()){
				  String message = new String(it.next().message()); 
				  try{
					  System.out.println(message);
					  index++;
					  logger.info("count index = {}", new Object[]{index});
				  }catch(Exception e){
					  logger.error("Bill consumer error, errorIndex={}, e={}, msg={}, cause={}", new Object[]{errorIndex, e, e.getMessage(), e.getCause()});
				  }
				 
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		new KafkaConsumerTest().start();
	}
}
