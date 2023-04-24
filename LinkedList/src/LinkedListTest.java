
// 리스트의 자료구조 ADT
/*
리스트는 선형으로 데이터가 쭉 이어지는 구조
1. 리스트 생성 : 초기화할 리스트의 주소 값 인자로 전달
2. 리스트의 맨 앞 부분에 데이터 저장
3. 리스트의 중간에 데이터 저장
4. 리스트에 데이터 추가
5. 데이터 삭제, 삭제된 데이터는 반환된다
6. 리스트에 저장되어 있는 데이터 수 반환
현재 데이터릍 조회하면 다음 저장된 데이터 위치를 알 수 있다.
리스트 내에서 데이터의 참조위치를 기록한다.
*/

/*
리스트는 그 전의 데이터에서 다음 데이터로 넘어갈 수 있어야 함
스택이나 큐와 차이는 중간에도 데이터 삽입이 가능하다는 점?
(데이터의 저장, 읽기, 삭제 위치 지정이 가능하며 자유롭다)
*/

/*
LinkedList
배열을 기반으로 하는 ArrayList의 배열 무한생성 단점을 보완하기 위해
순차적 메모리와 인덱스라는 특성을 기반으로 데이터를 사용하는 배열과 다르게, 메모리의 동적 할당을 이용

하나의 노드에는 다음 노드의 주소 (Node형 참조객체), 데이터 담을 것이 필요
index 변수를 두면 더 편하지 않을까? -> 리스트의 크기는 계속 체크해줄 변수가 필요하다.
다음 노드를 찾아가는법 -> 참조형 객체를 잘 이용

[LinkedList ADT]
1. 리스트 생성
: 리스트의 주소 값을 변수로 전달
2. 처음 데이터 초기화??
3. 데이터 추가
새로운 노드 생성, 이전 노드의 Node형 참조객체에 주소 연결해주기
4. 데이터 삭제
데이터 삭제 후 반환, 노드끼리의 이어짐에 신경
5. 개수 반환
리스트 크기 반환
6. 정렬 : 전, 후의 데이터를 비교해 그 순서를 바꿔나가는 방식으로 정렬을 구현할 수 있지 않을까?
 */
public class LinkedListTest {
    public static void main(String[] args) {
        LinkedListt li = new LinkedListt();

        // add test
        System.out.println("add test");
        li.add(1);
        li.add(2);
        li.add(3);
        li.add("hg");
        li.print();
        System.out.println();

        // contains test
        System.out.println("contains test");
        System.out.println(li.contatins(3));
        System.out.println();

        // index delete test
        System.out.println("delete test 1");
        System.out.println("지워진 요소 :" +li.delete(1));
        li.print();
        System.out.println();

        // Object delete test
        System.out.println("delete test 2");
        System.out.println("지워진 요소 :" + li.delete("dd"));
        li.print();
        System.out.println();

        // size test
        System.out.println("size test");
        System.out.println(li.size);
        System.out.println();

        // empty test
        System.out.println("empty test");
        li.empty();
        li.print();
    }

}

class LinkedListt {
    // 1. 리스트 생성 : 리스트의 주소 값을 변수로 전달
    LinkedListt (){}

    // 2. 리스트의 맨 처음 노드 (Head)
    node Head = new node(null);
    int size = 0;

    // 3. 데이터 추가
    void add(Object n){

        // 1. Head 뒤에 새로운 노드를 추가해 주는 방법
        // 새로운 node 생성, 새로운 node의 next 변수에 기존 Head의 next 변수로 연결된 주소 담기, 이후 Head.next 변수에 Next의 주소 담기
        // 하지만 이러면 인덱스를 통해 데이터를 찾아갈 수 없다. (먼저 들어온 데이터가 뒤로 가게 됨)
        // 이 경우 새로운 노드 - 기존 노드 연결이 필요
        /*
        node Next = new node(n);
        Next.next = Head.next;
        Head.next = Next;
        size++
        ;*/

        // 2. 맨 끝에 새로운 노드를 추가하는 방법
        // 새로운 노드르 생성, 데이터 입력
        // 노드를 순회해서 맨 끝 노드를 찾기(data가 null인 곳), 새로운 노드를 연결
        // 맨 끝 노드 찾는 것에는 참조형 객체 기본값 null 이용

        node x = Head;
        int i =0;
        do{
            if(x.data==null){
                x.data = n;
            } else if (x.next==null) {
                x.next = new node(n);
                x = x.next;
            } else{
                x = x.next;
            }
            i++;
        }while (i<size);
        size++;
    }

    // 5. 데이터 조회
    // x에 x.next의 값을 계속 대입하면서 노드를 하나씩 앞으로 순회
    Object get(int index){
        if(index<0||size<=index){
            throw new ArrayIndexOutOfBoundsException();
        }
        node x = Head;
        int i = 0;
        while(i<index){
            x = x.next;
            i++;
        }
        return x.data;
    }

    // 6. 데이터 존재여부 확인
    // LinkedList를 순회하며 data의 값을 대조해봄
    Boolean contatins(Object n){
        node x = Head;
        for (int i = 0; i < size; i++) {
            if(x.data==n){
                return true;
            }
            x = x.next;
        }
        return false;
    }

    // 6.1 인덱스에 위치한 데이터 삭제
    // 인덱스로 받아 그 위치의 데이터를 삭제하는 경우
    // 반복문을 통한 순회, to가 지울 노드에 도달하기까지, before 노드로 삭제할 노드 이전 노드도 함께 찾아둔다.
    // 삭제할 데이터를 반환해줄 변수에 대입
    // 삭제할 노드 이전 노드의 node next 변수에 to next 주소값을 연결해줌
    Object delete(int index){
        node to = Head;
        node before = Head;
        Object deleted;

        // 인덱스 범위 초과시 오류 발생
        if(index<0||size<=index){
            throw new ArrayIndexOutOfBoundsException();
        }

        int i = 0;
        while(i<index){
            before = to;
            to = to.next;
            i++;
        }
        deleted = to.data;
        before.next = to.next;
        size--;
        return deleted;
    }

    // 6.2 특정값을 가진 위치한 데이터 삭제 (입력에서 int, Object 값 구분하기가 쉽지 않다)
    // 리스트 순회하며 값이 있는지 확인, 있으면 거기서 순회 멈춤, 없는 경우는 boolean으로 확인
    Object delete(Object n){
        node before = Head;
        node to = Head;
        boolean check = false;

        int i = 0;
        while(i<size){
            if(to.data==n){
                check =true;
                break;
            }
            else {
            before = to;
            to = to.next;
            i++;
            }
        }

        if(check){
            Object deleted = to.data;
            before.next =to.next;
            size--;
            return deleted;
        }
        else {
            System.out.println("찾는 데이터가 없습니다");
            return null;
        }
    }

    // 7. 리스트 크기 반환
    int size (){
        return size-1;
    }

    // 8. 리스트 초기화
    void empty(){
        Head.data =null;
        Head.next =null;
        size =0;
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