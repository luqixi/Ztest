package com.linkstec.kafka;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import net.sf.json.JSONObject;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaProducerTest {
	private static final Logger logger = LoggerFactory.getLogger(KafkaProducerTest.class);

	public static void main(String[] args) {

		/*
		 * Properties props = new Properties(); props.put("bootstrap.servers",
		 * "10.189.110.225:9092,10.189.110.226:9092,10.189.110.227:9092");
		 * props.put("bootstrap.servers",
		 * "kafka01:9092,kafka02:9092,kafka03:9092"); props.put("acks", "1");
		 * props.put("retries", 3); props.put("batch.size", 16384);
		 * props.put("linger.ms", "100"); props.put("buffer.memory", 33554432);
		 * props.put("key.serializer",
		 * "org.apache.kafka.common.serialization.StringSerializer");
		 * props.put("value.serializer",
		 * "org.apache.kafka.common.serialization.StringSerializer");
		 */
		Properties props = new Properties();

		// props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
		// "10.189.110.225:9092,10.189.110.226:9092,10.189.110.227:9092");
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
//		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "codis01:9092,codis02:9092,codis03:9092");

		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				"org.apache.kafka.common.serialization.StringSerializer");
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

		props.put(ProducerConfig.ACKS_CONFIG, "1");

		props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, "33554432");
		props.put(ProducerConfig.BATCH_SIZE_CONFIG, "1048576");
		props.put(ProducerConfig.MAX_REQUEST_SIZE_CONFIG, "5242880");
		props.put(ProducerConfig.LINGER_MS_CONFIG, "100");
		props.put(ProducerConfig.RETRIES_CONFIG, 3);

		System.out.println("kafka producer start ##################################");
		Producer<String, String> producer = new KafkaProducer<String, String>(props);

		for (int i = 0; i < 1; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("MSG_ID", "200000000247BD831C8D0F24");
			map.put("SRC", "15");
			map.put("MSG_TYPE", "ICONV_EVT");
			map.put("MSG_SUBTYPE", "TRANS");
			map.put("SEND_DATE", 20170227);
			map.put("SEND_TIME", 91531);

			Map<String, Object> subMap = new HashMap<String, Object>();
			subMap.put("USER_CODE", "41001");
			subMap.put("CUST_NO", "2400006");
			subMap.put("FUND_ID", "17514");
			subMap.put("SC_SNO", "800031");
			subMap.put("BK_SNO", "80003046242");
			subMap.put("SOURCE_TYPE", "1");
			subMap.put("TRANS_TYPE", "1");
			subMap.put("DIRECTION", "1");
			subMap.put("BANK_NO", "6001");
			subMap.put("ORGID", "3306");
			subMap.put("MONEY_TYPE", "0");
			subMap.put("BALANCE", "880000");
			subMap.put("TRD_DATE", "20170309");
			subMap.put("TRD_TIME", "133018");
			map.put("CONTENT", subMap);
			String jsontest = JSONObject.fromObject(map).toString();
			System.out.println(jsontest);
			String topic = "test";
//			String topic = "BankingSuritiesTransfer";
//			String topic = "StockAnn";
			ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, jsontest);
			producer.send(record, new Callback() {
				@Override
				public void onCompletion(RecordMetadata metaData, Exception exception) {
					if (exception != null) {
						logger.error(exception.getMessage());
						if (metaData != null) {
							logger.error(metaData.toString());
						}
					}
				}
			});
		}

		producer.close();

		System.out.println("kafka producer stop ##################################");

	}

	/*
	 * private final Producer<String, String> producer; public final static
	 * String TOPIC = "bill_test";
	 * 
	 * private KafkaProducer(){ Properties props = new Properties();
	 * //此处配置的是kafka的端口 props.put("metadata.broker.list",
	 * "10.180.224.191:9092,10.180.224.192:9092,10.180.224.193:9092");
	 * 
	 * //配置value的序列化类 props.put("serializer.class",
	 * "kafka.serializer.StringEncoder"); //配置key的序列化类
	 * props.put("key.serializer.class", "kafka.serializer.StringEncoder");
	 * 
	 * //request.required.acks //0, which means that the producer never waits
	 * for an acknowledgement from the broker (the same behavior as 0.7). This
	 * option provides the lowest latency but the weakest durability guarantees
	 * (some data will be lost when a server fails). //1, which means that the
	 * producer gets an acknowledgement after the leader replica has received
	 * the data. This option provides better durability as the client waits
	 * until the server acknowledges the request as successful (only messages
	 * that were written to the now-dead leader but not yet replicated will be
	 * lost). //-1, which means that the producer gets an acknowledgement after
	 * all in-sync replicas have received the data. This option provides the
	 * best durability, we guarantee that no messages will be lost as long as at
	 * least one in sync replica remains.
	 * props.put("request.required.acks","-1");
	 * 
	 * producer = new Producer<String, String>(new ProducerConfig(props)); }
	 * 
	 * void produce() { int messageNo = 1000; final int COUNT = 1000000;
	 * 
	 * while (messageNo < COUNT) { String key = String.valueOf(messageNo);
	 * String data = "hello kafka message " + key; producer.send(new
	 * KeyedMessage<String, String>(TOPIC, key ,data));
	 * System.out.println(data); messageNo ++; } }
	 * 
	 * public static void main( String[] args ) { new KafkaProducer().produce();
	 * }
	 */
}
