package chengweiou.universe.wormhole.service;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import chengweiou.universe.blackhole.exception.FailException;
import chengweiou.universe.blackhole.model.Builder;
import chengweiou.universe.wormhole.data.Data;
import chengweiou.universe.wormhole.model.SearchCondition;
import chengweiou.universe.wormhole.model.entity.ReqRecord;
import chengweiou.universe.wormhole.service.reqrecord.ReqRecordService;

@SpringBootTest
@ActiveProfiles("test")
public class ReqRecordTest {
	@Autowired
	private ReqRecordService service;
	@Autowired
	private Data data;

	@Test
	public void saveDelete() throws FailException {
		ReqRecord e = Builder.set("url", "127.0.0.1:60000/wormhole/test-save").set("ip", "192.168.1.1").set("browser", "chrome").to(new ReqRecord());
		service.save(e);
		Assertions.assertEquals(true, e.getId() > 0);
		service.delete(e);
	}

	@Test
	public void update() {
		ReqRecord e = Builder.set("id", data.reqRecordList.get(0).getId()).set("os", "windows").to(new ReqRecord());
		long count = service.update(e);
		Assertions.assertEquals(1, count);

		ReqRecord indb = service.findById(data.reqRecordList.get(0));
		Assertions.assertEquals("windows", indb.getOs());

		Builder.set("os", "mac").to(e);
		service.update(e);
	}

	@Test
	public void count() {
		long count = service.count(new SearchCondition(), null);
		Assertions.assertEquals(2, count);
	}

	@Test
	public void find() {
		SearchCondition searchCondition = Builder.set("k", "192.168.1.1").to(new SearchCondition());
		List<ReqRecord> list = service.find(searchCondition, null);
		Assertions.assertEquals(1, list.size());
		Assertions.assertEquals("mac", list.get(0).getOs());
	}

	@BeforeEach
	public void init() {
		data.init();
	}
}
