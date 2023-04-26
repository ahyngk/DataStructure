import javax.xml.soap.Node;
import java.util.LinkedList;
import java.util.Queue;

public class Test {
    public static void main(String[] args) {
        int [] ar = {1,2,3,4,5,6,7,8,9,10,11};
        ArrayTree at = new ArrayTree(ar);
        at.preOrderTravel(0);
        System.out.println();
        at.inOrderTravel(0);
        System.out.println();
        at.postOrderTravel(0);
        System.out.println();
        at.levelTravel(0);
        System.out.println();

        ListTree lt = new ListTree(ar);
        lt.preOrderTravel(lt.Head);
        System.out.println();
        lt.inOrderTravel(lt.Head);
        System.out.println();
        lt.postOrderTravel(lt.Head);
        System.out.println();
        lt.levelTraver();

    }
}

class ArrayTree{
    int[] arrayTree;
    int index;
    ArrayTree(int[]arr){
        arrayTree=arr;
        index =arrayTree.length;
    }
    // 이진 트리 순회의 재귀
    // 맨 아래까지 간 후 다시 돌아오는 것이 문제였는데, 재귀 형태로 잘게 쪼개서 구현하면 자연스럽게 돌아와진다.
    // if문 두개로 구성하면 if문 하나 실행(재귀호출) 이후 다음 if 문으로 넘어가기 때문에 가능
    // 가장 작은 트리 단위로 보고, 부모 노드에게 선택지를 주는 개념으로
    void preOrderTravel (int a){
        System.out.print(arrayTree[a]+", ");
        int left = a*2+1;
        int right = a*2+2;

        if(left<index){
            preOrderTravel(left);
        }
        if(right<index){
            preOrderTravel(right);
        }
    }

    void inOrderTravel(int travelIndex){
        int left = travelIndex*2+1;
        int right = travelIndex*2+2;
        if(left<index){
            inOrderTravel(left);
        }
        System.out.print(arrayTree[travelIndex]+", ");
        if(right<index){
            inOrderTravel(right);
        }
    }

    void postOrderTravel(int travelIndex){
        int left = travelIndex*2+1;
        int right = travelIndex*2+2;
        if(left<index){
            postOrderTravel(left);
        }
        if(right<index){
            postOrderTravel(right);
        }
        System.out.print(arrayTree[travelIndex]+", ");

    }

    // 완전 이진트리로 가정하면 log로 높이를 구할 수 있다.
    // log2()가 없으므로 로그 밑 변환 공식을 사용
    // 레벨별로 나누어서 출력
    void levelTravel(int travelIndex){
        int height = (int)(Math.log(11)/Math.log(2));
        System.out.println(height);
        for (int i = 0; i <=height ; i++) {
            int count =1;
            while (count<=Math.pow(2,i) && travelIndex<index){
                System.out.print(arrayTree[travelIndex]+", ");
                travelIndex++;
                count++;
            }
            System.out.println();
        }
    }
}

class ListTree{
    node Head;
    ListTree(int [] arr){
        // node 배열을 생성, 배열 내 각 객체 생성
        node[] nodes = new node[arr.length];
        for (int i = 0; i < nodes.length ; i++) {
            nodes[i] = new node(arr[i]);
        }
        Head = nodes[0];
        System.out.println(Head.data);
        // 각 노드를 돌아가면서 왼쪽, 오른쪽 노드를 연결시켜준다.
        // 그럼 위에서부터 노드가 순차적으로 연결되기 때문에 index에는 따로 신경을 쓰지 않아도 됨.
        // 배열 기반의 구현과 같은 형태로 구현됨.
        // parent 노드도 이때 정의해둔다.
        for (int i = 0; i < arr.length; i++) {
            int left = i*2+1;
            int right = i*2+2;
            if(left<arr.length){
                nodes[i].left=nodes[left];
                nodes[left].parent =nodes[i];
            }
            if(right<arr.length){
                nodes[i].right=nodes[right];
                nodes[right].parent=nodes[i];
            }
        }
    }

    // 꼭 입력값으로 노드를 주어야 함. 메서드 실행을 할 때 입력되도록 해야 함
    // 앞에서 만든 Head로 하려고 하면 클래스 객체가 생성되기 이전이라서 그런지 데이터가 모두 기본값으로 되어 있음
    // 재귀적 특성때문에 Head를 바로 메서드 내에서 가져다 사용할 수 없는 것 같기도 하다.(계속 초기화 되기 때문..?)
    void preOrderTravel(node N){
        System.out.print(N.data+", ");
        if(N.left!=null){
            preOrderTravel(N.left);
        }
        if(N.right!=null){
            preOrderTravel(N.right);
        }
    }

    void inOrderTravel(node N){
        if(N.left!=null){
            inOrderTravel(N.left);
        }
        System.out.print(N.data+", ");
        if(N.right!=null){
            inOrderTravel(N.right);
        }
    }

    void postOrderTravel(node N){
        if(N.left!=null){
            postOrderTravel(N.left);
        }
        if(N.right!=null){
            postOrderTravel(N.right);
        }
        System.out.print(N.data+", ");
    }

    void levelTraver(){
        Queue<node> q = new LinkedList<node>();
        node N;
        q.add(Head);
        while (!q.isEmpty()){
            N=q.poll();
            System.out.print(N.data+", ");
            if(N.left!=null){
                q.add(N.left);
            }
            if(N.right!=null){
                q.add(N.right);
            }
        }
    }


}
class node{
    int data;
    node left;
    node right;
    node parent;
    node(){};
    node(int a){
        data=a;
    }
}