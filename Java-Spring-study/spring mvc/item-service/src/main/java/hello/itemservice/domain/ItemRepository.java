package hello.itemservice.domain;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store =new HashMap<>();// 멀티쓰레드 환경에서 접근하면 값이 꼬일수 있게 때문에 원래는 Hashmap 쓰면 안되고 컨커런트해쉬맵 써야함
    private static long sequence = 0L; // 얘도 마찬가지로 다른거 써야함

    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values()); // 한번 감싸서 store자체를 반환해도 되지만 new ArrayList로 반환하는 이유는 반환 후에 값에 변경이 생겨도 store가 변하지 않기 때문
    }

    public void update(Long itemId, Item updateParam) {
        Item findItem = findById(itemId);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
        //원래의 경우에는 Item의 id 객체만 set이 되지 않기 때문에 id를 제외한 나머지 변수 객체를 만드는게 맞지만 프로젝트가 작으니까 그대로 사용, 명확성을 위해서
    }

    public void clearStroe() {
        store.clear();
    }


}
