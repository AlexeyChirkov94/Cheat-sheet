package ru.chirkov.cheat.sheet.algorithms.and.data.structures.binary.tree;

public class WithMain {


    public static void main(String[] args) {
        /*
        *                        50
        *                     /     \
        *                    30     90
        *                  /   \   /   \
        *                 27  37  85   115
        *                /  \         /   \
        *              10   28      110   120
        *             /  \          /    /   \
        *            3    18      100  117   180
        *                  \           /
        *                   19       116
        *                    \
        *                    20
        * */

        MyTree<DataPerson, Integer> myTree = new MyTree<>((a,b)->{
            if (a > b) return 1;
            else if (a < b) return -1;
            else return 0;
        }, DataPerson::getId);
        fillTree(myTree);

        System.out.println("dot - insert finish");
        System.out.println("find = " + myTree.find(180));
    }

    private static void fillTree(MyTree myTree){
        myTree.insert(new DataPerson(50));
        myTree.insert(new DataPerson(30));
        myTree.insert(new DataPerson(90));
        myTree.insert(new DataPerson(27));
        myTree.insert(new DataPerson(37));
        myTree.insert(new DataPerson(85));
        myTree.insert(new DataPerson(115));
        myTree.insert(new DataPerson(10));
        myTree.insert(new DataPerson(28));
        myTree.insert(new DataPerson(110));
        myTree.insert(new DataPerson(120));
        myTree.insert(new DataPerson(3));
        myTree.insert(new DataPerson(18));
        myTree.insert(new DataPerson(19));
        myTree.insert(new DataPerson(20));
        myTree.insert(new DataPerson(100));
        myTree.insert(new DataPerson(117));
        myTree.insert(new DataPerson(180));
        myTree.insert(new DataPerson(116));
    }
}
