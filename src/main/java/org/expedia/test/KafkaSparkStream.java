package org.expedia.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.spark.streaming.Duration;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

public class KafkaSparkStream {
    public static void main(String[] args) {
        JavaStreamingContext context = new JavaStreamingContext("local", "lz_omniture_stream", new Duration(10000L));

        Map<String, Integer> topic = new HashMap<String, Integer>();
        Integer partition = Integer.valueOf(1);
        topic.put("test", partition);

        JavaPairDStream<String, String> messadges = KafkaUtils.createStream(context, "localhost:2181", "test-consumer-group", topic);

        // JavaDStream<String> words = messadges.map(new RunStream);

        messadges.print();

        context.start();
        context.awaitTermination();
    }
}