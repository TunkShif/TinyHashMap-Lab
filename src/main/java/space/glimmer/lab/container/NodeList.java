package space.glimmer.lab.container;

import java.util.LinkedList;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 链表的实现,需要完成除了getType的其他接口
 * 你可以选择自己手写一遍实现,也可以学习使用Java 泛型容器里的LinkedList实现好了的链表直接写这里
 */
public class NodeList implements BucketContainer{

    private LinkedList<Entry> list;

    public NodeList() {
        list = new LinkedList<>();
    }

    /**
     * 写死的,不能修改,用来判断具体的数据结构
     * @return
     */
    @Override
    public String getType() {
        return "nodelist";
    }

    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        for (Entry e: list) {
            if (e.key == null && key == null) return e;
            if (e.key != null && e.key.equals(key)) return e;
        }
        return null;
    }

    /**
     * 添加一个元素
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        list.add(e);
    }


    /**
     * 更新一个节点
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        Entry currentEntry = this.searchElement(e.key);
        if (currentEntry != null) {
            currentEntry.value = e.value;
        }
    }

    /**
     * 删除一个节点
     * @param key
     */
    @Override
    public void removeElement(String key) {
        Entry currentEntry = this.searchElement(key);
        if (currentEntry != null) {
            list.remove(currentEntry);
        }
    }

    /**
     * 遍历并返回所有元素
     * @return
     */
    @Override
    public Entry[] traverse() {
        return list.toArray(new Entry[0]);
    }

    @Override
    public int getSize() {
        return list.size();
    }
}
