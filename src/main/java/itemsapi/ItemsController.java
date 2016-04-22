package itemsapi;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/items")
public class ItemsController {

	private Map<Long, Item> items = new ConcurrentHashMap<>();
	private AtomicLong lastId = new AtomicLong();
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public Collection<Item> items(){
		return items.values();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Item> getItem(@PathVariable long id){
		
		Item item = items.get(id);
		
		if (item != null){
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Item addItem(@RequestBody Item item){
		
		long id = lastId.incrementAndGet();
		item.setId(id);
		items.put(id, item);
		
		return item;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Item> updateItem(@PathVariable long id, @RequestBody Item item){
		
		Item currentItem = items.get(id);
		
		if (currentItem != null){
			item.setId(id);
			items.put(id, item);
			
			return new ResponseEntity<Item>(item, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Item> deleteItem(@PathVariable long id){
		
		Item currentItem = items.remove(id);
		
		if (currentItem != null){
			return new ResponseEntity<Item>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
