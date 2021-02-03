package space.glimmer.lab.container;

import java.util.ArrayList;
import java.util.function.Consumer;

/**
 * @author Lehr
 * @create: 2021-01-16
 * 二叉搜索树实现,需要完成除了getType的其他接口
 */
public class Bst implements BucketContainer {

    Entry entry;
    Bst left;
    Bst right;

    public Bst(Entry e) {
        this(e, null, null);
    }

    public Bst(Entry e, Bst l, Bst r) {
        this.entry = e;
        this.left = l;
        this.right = r;
    }

    /**
     * 写死的,不能修改
     *
     * @return
     */
    @Override
    public String getType() {
        return "bst";
    }


    /**
     * 搜索某个元素并返回
     * 其中key是这个元素Entry的key
     *
     * @param key
     * @return
     */
    @Override
    public Entry searchElement(String key) {
        Bst target = search(this, key);
        return target == null ? null : target.entry;
    }

    /**
     * 添加一个元素
     *
     * @param e
     */
    @Override
    public void addElement(Entry e) {
        insert(this, e);
    }

    /**
     * 更新一个节点
     *
     * @param e
     */
    @Override
    public void updateElement(Entry e) {
        Bst target = search(this, e.key);
        if (target != null) target.entry.value = e.value;
    }

    /**
     * 删除一个节点
     *
     * @param key
     */
    @Override
    public void removeElement(String key) {
        remove(this, key);
    }

    /**
     * 按照树的"先序遍历",遍历并返回所有元素
     *
     * @return
     */
    @Override
    public Entry[] traverse() {
        ArrayList<Entry> entries = new ArrayList<>();
        preorderTraverse(this, entries::add);
        return entries.toArray(new Entry[0]);
    }

    @Override
    public int getSize() {
        return traverse().length;
    }

    private static int hash(String key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    private static void insert(Bst root, Entry e) {
        if (hash(e.key) < hash(root.entry.key)) {
            if (root.left == null)
                root.left = new Bst(e);
            else
                insert(root.left, e);
            return;
        }
        if (hash(e.key) > hash(root.entry.key)) {
            if (root.right == null)
                root.right = new Bst(e);
            else
                insert(root.right, e);
        }
    }

    private static Bst search(Bst root, String key) {
        if (root.entry == null) return null;
        if (key == null) {
            if (root.entry.key == null) return root;
        } else {
            if (root.entry.key.equals(key)) return root;
        }
        if (hash(key) <= hash(root.entry.key)) {
            if (root.left == null) {
                return null;
            } else {
                return search(root.left, key);
            }
        } else {
            if (root.right == null) {
                return null;
            } else {
                return search(root.right, key);
            }
        }
    }

    private static Bst remove(Bst node, String key) {
        // return the new root of subtree
        if (node == null) return null;
        if (hash(key) < hash(node.entry.key)) {
            node.left = remove(node.left, key);
        } else if (hash(key) > hash(node.entry.key)) {
            node.right = remove(node.right, key);
        } else if (node.left != null && node.right != null) {
            node.entry = findMin(node.right).entry;
            node.right = remove(node.right, node.entry.key);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }
        return node;
    }

    private static Bst findMin(Bst root) {
        if (root == null) return null;
        Bst target = root;
        while (target.left != null) target = target.left;
        return target;
    }

    private static void preorderTraverse(Bst root, Consumer<Entry> f) {
        if (root != null) {
            f.accept(root.entry);
            preorderTraverse(root.left, f);
            preorderTraverse(root.right, f);
        }
    }
}
