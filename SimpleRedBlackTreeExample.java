enum Color {
    RED,
    BLACK
}

class SimpleNode {
    int value;
    Color color;
    SimpleNode left;
    SimpleNode right;

    SimpleNode(int value, Color color) {
        this.value = value;
        this.color = color;
        this.left = null;
        this.right = null;
    }
}

public class SimpleRedBlackTreeExample {
    public static void printTree(SimpleNode node, String prefix) {
        if (node != null) {
            System.out.println(prefix + node.value + "(" + node.color + ")");
            printTree(node.left, prefix + "-");
            printTree(node.right, prefix + "-");
        }
    }

    private static boolean isRed(SimpleNode node) {
        if (node == null) {
            return false;
        }
        return node.color == Color.RED;
    }

    private static SimpleNode rotateLeft(SimpleNode node) {
        SimpleNode x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Color.RED;
        return x;
    }

    private static SimpleNode rotateRight(SimpleNode node) {
        SimpleNode x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Color.RED;
        return x;
    }

    private static void flipColors(SimpleNode node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }

    private static SimpleNode insert(SimpleNode node, int value, Color color) {
        if (node == null) {
            return new SimpleNode(value, color);
        }

        if (value < node.value) {
            node.left = insert(node.left, value, color);
        } else if (value > node.value) {
            node.right = insert(node.right, value, color);
        } else {
            node.color = color;
        }

        // Проверка и балансировка
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        return node;
    }

    public static void main(String[] args) {
        SimpleNode root = null;
        root = insert(root, 10, Color.BLACK);
        root = insert(root, 5, Color.RED);
        root = insert(root, 15, Color.BLACK);
        root = insert(root, 3, Color.BLACK);
        root = insert(root, 7, Color.BLACK);
        root = insert(root, 13, Color.RED);
        root = insert(root, 17, Color.RED);

        printTree(root, "");
    }
}