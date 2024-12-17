import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomizedBST implements MafiaInterface {
    private static class TreeNode {
        private Suspect item;
        private TreeNode left; // pointer to left subtree
        private TreeNode right; // pointer to right subtree
        private int N = 0; //number of nodes in the subtree rooted at this TreeNode

        //Constructor
        TreeNode() {
            this.item = null;
            this.left = null;
            this.right = null;
            N++;
        }
        //Getters and setters
        void setItem(Suspect item) { this.item = item; }

        Suspect getItem() { return item; }

        void setLeft(TreeNode left) { this.left = left; N++; }

        TreeNode getLeft() { return left; }

        void setRight(TreeNode right) { this.right = right; N++; }

        TreeNode getRight() { return right; }

        int getN() { return N; }

        void setN() { N--; }
    };

    private TreeNode root = null; //root of the tree

    public void insert(Suspect item) {
        root = insert_r(item, root);   // insert recursively
    }

    private TreeNode insert_r(Suspect item, TreeNode h) {
        if (h == null) {
            TreeNode node = new TreeNode();
            node.setItem(item);
            return node;
        }
        if (Math.random() * h.getN() < 1.0) {
            return insert_as_root(item, h);
        }
        if (h.getItem().key() < item.key()) {
            h.setRight(insert_r(item, h.getRight()));
        }
        else if (h.getItem().key() > item.key()){
            h.setLeft(insert_r(item, h.getLeft()));
        }
        return h;

    }

    private TreeNode insert_as_root(Suspect item, TreeNode h) {
        if (h == null) {
            TreeNode node = new TreeNode();
            node.setItem(item);
            return node;
        }
        if (h.getItem().key() < item.key()) {
            h.setRight(insert_as_root(item, h.getRight()));
            h = left_rotation(h);
        }
        else if (h.getItem().key() > item.key()){
            h.setLeft(insert_as_root(item, h.getLeft()));
            h = right_rotation(h);
        }
        return h;
    }

    private TreeNode right_rotation(TreeNode h) {
        TreeNode node = h.getLeft();
        h.setLeft(node.getRight());
        node.setRight(h);
        return node;
    }

    private TreeNode left_rotation(TreeNode h) {
        TreeNode node = h.getRight();
        h.setRight(node.getLeft());
        node.setLeft(h);
        return node;
    }

    public void load(String filename) {
        Scanner file;
        try {
            file = new Scanner(new File(filename));

            while (file.hasNext()) {
                Suspect item = new Suspect();
                item.setAFM(file.nextInt());
                item.setFirstName(file.next());
                item.setLastName(file.next());
                item.setSavings(file.nextDouble());
                item.setTaxedIncome(file.nextDouble());
                insert(item);
            }
            System.out.println("The insertion done successfully");
        }
        catch (FileNotFoundException e) { System.out.println("File not found"); }
    }

    public void updateSavings(int AFM, double savings) {
        Suspect item = searchByAFM_r(AFM, root);
        if (item != null) {
            item.setSavings(savings);
            System.out.println("The savings of the given AFM are updated");
        }
        else {
            System.out.println("The given AFM does not exist");
        }
    }

    public Suspect searchByAFM(int AFM) {
        return searchByAFM_r(AFM, this.root);
    }

    private Suspect searchByAFM_r(int AFM, TreeNode h) {
        if (h == null) { return null; }
        if (h.getItem().getAFM() == AFM) { return h.getItem(); }
        if (h.getItem().getAFM() < AFM) {
            return searchByAFM_r(AFM, h.getRight());
        }
        else {
            return searchByAFM_r(AFM, h.getLeft());
        }
    }

    public DoublyLinkedList searchByLastName(String last_name) {
        DoublyLinkedList list = searchByLastName_r(last_name, root);
        if (list != null && list.size() <= 5) {
            for (int i=0; i<list.size(); i++) {
                Suspect item = (Suspect) list.getItem(i);
                System.out.println("Last Name: " + item.getLastName() + " First Name: " + item.getFirstName() + " Savings: " + item.getSavings() + " Taxed Income: " + item.getTaxedIncome());
            }
        }
        return list;
    }

    private DoublyLinkedList searchByLastName_r(String last_name, TreeNode h) {
        if (h == null) { return null; }
        DoublyLinkedList<Suspect> list = new DoublyLinkedList<>();

        if (h.getItem().getLastName().equals(last_name)) {
            list.addFirst(h.getItem());
            searchByLastName_r(last_name, h.getLeft());
            searchByLastName_r(last_name, h.getRight());
        }
        if (h.getItem().getLastName().compareTo(last_name) < 0) {
            searchByLastName_r(last_name, h.getRight());
        }
        else {
            searchByLastName_r(last_name, h.getLeft());
        }
        return list;
    }

    public void remove(int AFM) {
        remove_r(AFM, root);    // remove recursively
        if (searchByAFM(AFM) != null) {
            root.setN();   // size of tree decreases by 1 if the suspect has been removed
        }
    }

    private TreeNode remove_r(int AFM, TreeNode h) {
        if (h == null) { return null; }
        if (h.getItem().key() > AFM) {
            h = remove_r(AFM, h.getLeft());
        }
        else if (h.getItem().key() < AFM) {
            h = remove_r(AFM, h.getRight());
        }
        // if the node is at root
        else {
            h = join(h.getLeft(), h.getRight());
        }
        return h;
    }

    private TreeNode join(TreeNode a, TreeNode b) {
        if (a == null) return b;
        if (b == null) return a;
        int n = a.getN() + b.getN();
        if (Math.random() * n < 1.0 * a.getN()) {
            a.setRight(join(a.getRight(), b));
            return a;
        }
        else {
            b.setLeft(join(a, b.getLeft()));
            return b;
        }
    }

    public double getMeanSavings() {
        return getMeanSavings_r(root) / root.getN();
    }

    private double getMeanSavings_r(TreeNode h) {
        double meanSavings = 0;
        if (h == null) { return meanSavings; }
        getMeanSavings_r(h.getLeft());
        meanSavings += h.getItem().getSavings();
        getMeanSavings_r(h.getRight());
        return meanSavings;
    }

    public void printTopSuspects(int k) {
        if (k <= root.getN()) {
            DoublyLinkedList<Suspect> list = traverseSuspects(root);
            System.out.println("The " + k + " most suspect depositors are: ");
            for (int i = 0; i < k; i++) {
                //System.out.println(list.getItem(i).getLastName() + " " + list.getItem(i).getFirstName());
                System.out.println(" ");
            }
        }
        else {
            System.out.println("The given k is greater than the size of the tree");
        }
    }

    private DoublyLinkedList<Suspect> traverseSuspects(TreeNode h) {
        DoublyLinkedList<Suspect> list = new DoublyLinkedList<>();
        if (h == null) { return null; }
        traverseSuspects(h.getLeft());
        list.addLast(h.getItem());
        traverseSuspects(h.getRight());
        return list;
    }

    public void printByAFM() {
        printByAFM_r(root);
    }

    private void printByAFM_r(TreeNode h) {
        if (h == null) { return; }
        printByAFM_r(h.getLeft());
        System.out.println("AFM: " + h.getItem().getAFM() + " Last Name: " + h.getItem().getLastName() + " First Name: " + h.getItem().getFirstName() + " Savings: " + h.getItem().getSavings() + " Taxed Income: " + h.getItem().getTaxedIncome());
        printByAFM_r(h.getRight());
    }

    public static void main(String[] args) {
        RandomizedBST bst = new RandomizedBST();    // initiallize a binary search tree
        // insert 2 suspects in order to use search and remove methods
        Suspect s1 = new Suspect();
        s1.setAFM(23456); s1.setLastName("Papadopoulos"); s1.setFirstName("Ioannis"); s1.setSavings(34521.23); s1.setTaxedIncome(14324.56);
        bst.insert(s1);
        Suspect s2 = new Suspect();
        s2.setAFM(75281); s2.setLastName("Antoniou"); s2.setFirstName("Maria"); s2.setSavings(345219.23); s2.setTaxedIncome(14324.56);
        bst.insert(s2);

        String answer = "";
        do {
            System.out.println("Give a number from 1 to 9 according to your needs: ");
            System.out.println("1. Insert a suspect \n2. Load a file \n3. Update the savings of a suspect \n4. Search a suspect by their AFM");
            System.out.println("5. Search a suspect by their last name \n6. Remove a suspect \n7. Get mean savings \n8. Print top suspects \n9. Print by AFM");

            Scanner sc = new Scanner(System.in);
            int num = sc.nextInt();

            switch (num) {
                case 1:
                    Suspect suspect = new Suspect();

                    System.out.println("Insert AFM");
                    int AFM = sc.nextInt();
                    suspect.setAFM(AFM);

                    System.out.println("Insert last name");
                    String lastName = sc.next();
                    suspect.setLastName(lastName);

                    System.out.println("Insert first name");
                    String firstName = sc.next();
                    suspect.setFirstName(firstName);

                    System.out.println("Insert savings");
                    double savings = sc.nextDouble();
                    suspect.setSavings(savings);

                    System.out.println("Insert taxed income");
                    double taxedIncome = sc.nextDouble();
                    suspect.setTaxedIncome(taxedIncome);

                    bst.insert(suspect);
                    break;

                case 2:
                    System.out.println("Give a pathname for your file");
                    String file = sc.next();
                    bst.load(file);
                    break;

                case 3:
                    System.out.println("Insert AFM");
                    AFM = sc.nextInt();
                    System.out.println("Insert savings");
                    savings = sc.nextDouble();
                    bst.updateSavings(AFM, savings);
                    break;

                case 4:
                    System.out.println("Give AFM");
                    AFM = sc.nextInt();
                    Suspect item = bst.searchByAFM(AFM);
                    if (item != null) {
                        System.out.println("Last Name: " + item.getLastName() + " First Name: " + item.getFirstName() + " Savings: " + item.getSavings() + " Taxed Income: " + item.getTaxedIncome());
                    }
                    else {
                        System.out.println("The given AFM does not exist");
                    }
                    break;

                case 5:
                    System.out.println("Give last name");
                    lastName = sc.next();
                    DoublyLinkedList list = bst.searchByLastName(lastName);
                    if (list == null) {
                        System.out.println("The given last name does not exist");
                    }
                    break;

                case 6:
                    System.out.println("Give AFM");
                    AFM = sc.nextInt();
                    bst.remove(AFM);
                    break;

                case 7:
                    System.out.println("The mean savings are: " + bst.getMeanSavings());
                    break;

                case 8:
                    System.out.println("How many top suspect you want to see? Give a number");
                    int k = sc.nextInt();
                    bst.printTopSuspects(k);
                    break;

                case 9:
                    bst.printByAFM();
                    break;
            }
            System.out.println("\nDo you want to continue? Type yes or no ");
            answer = sc.next();
            while (!(answer.equals("yes") || answer.equals("no"))) {
                System.out.println("Please type yes or no: ");
                answer = sc.next();
            }

        } while (answer.equals("yes"));

        System.out.println("Exit");
    }
}
