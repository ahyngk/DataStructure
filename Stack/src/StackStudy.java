public class StackStudy {
    public static void main(String[] args) {

        // LinkedList로 Stack 구현
        StackLinkedList sl = new StackLinkedList();

        System.out.println("[push test]");
        sl.push(1);
        sl.push(2);
        sl.push(3);
        sl.push(4);
        sl.push(5);
        sl.print();
        System.out.println();

        System.out.println("[peek test]");
        System.out.println("맨 끝 데이터: "+sl.peek());
        sl.print();
        System.out.println();

        System.out.println("[pop test]");
        System.out.println("삭제된 데이터: "+sl.pop());
        sl.print();
        System.out.println();

        System.out.println("[size test]");
        System.out.println(sl.size);

    }
}

class StackLinkedList {
    // 1. 리스트 생성 : 리스트의 주소 값을 변수로 전달
    StackLinkedList (){}
    // 2. 리스트의 맨 처음 노드 (Head)
    node Head = new node(null);
    node Tail = Head;
    int size = 0;

    // 3. push 데이터 추가
    void push(Object n){
        node x = Head;
        int i =0;
        do{
            if(x.data==null){
                x.data = n;
            } else if (x.next==null) {
                x.next = new node(n);
                x = x.next;
                Tail = x;
            } else{
                x = x.next;
            }
            i++;
        }while (i<size);
        size++;
    }

    // 4. peek 맨 끝 데이터 조회
    // 맨 끝인걸 알고 있으므로 tail node를 계속 마크업해서 사용
    Object peek(){
        return Tail.data;
    }

    // 5. pop 맨 뒤 데이터 반환 및 삭제
    Object pop (){
        if (Head.data==null){
            throw new ArrayIndexOutOfBoundsException();
        } else if (Tail==Head) {
            Object to = Head.data;
            Head.data =null;
            Head.next =null;
            size--;
            return to;
        } else{
            node before = Head;
            while(before.next!=Tail){
                before = before.next;
            }
            Object toreturn = Tail.data;
            before.next=null;
            Tail = before;
            size--;
            return toreturn;
        }
    }

    // 7. 리스트 크기 반환
    int size (){
        return size-1;
    }

    // 9. 리스트 순차 출력
    void print(){
        node x = Head;
        System.out.print("[");
        for (int i = 0; i < size-1; i++) {
            System.out.print(x.data+",");
            x = x.next;
        }
        System.out.print(x.data);
        System.out.print("]");
        System.out.println();
    }
}

class node {
    node next = null;
    Object data =0;

    // node 생성자
    node(Object b){
        data =b;
    }
    node(node next, Object b){
        this.next = next;
        data =b;
    }
}