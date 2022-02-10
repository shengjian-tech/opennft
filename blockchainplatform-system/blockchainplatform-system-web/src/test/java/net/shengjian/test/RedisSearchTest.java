package net.shengjian.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * redisearch Test
 *
 * @author AngeryFeather
 * @version 2021年4月14日 下午5:44:36
 * @copyright
 * @see RedisSearchTest
 */
@SpringBootTest
public class RedisSearchTest {

    @Test
    public void createIndexTest() {
		/*
		Client client = RedisSearchUtils.getClient();
		
		Schema schema = new Schema().addTextField("first", 1.0).addTextField("last", 1.0).addNumericField("age");
		IndexDefinition definition = new IndexDefinition()
	          .setPrefixes(new String[] {"student:", "pupil:"});
		System.out.println(client.createIndex(schema, Client.IndexOptions.defaultOptions().setDefinition(definition)));

		 */
    }

}
