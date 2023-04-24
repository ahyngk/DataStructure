import java.util.ArrayList;

// 리스트의 자료구조 ADT
/*
리스트는 선형으로 데이터가 쭉 이어지는 구조
1. 리스트 생성 : 초기화할 리스트의 주소 값 인자로 전달
2. 리스트의 맨 앞 부분에 데이터 저장
3. 리스트의 중간에 데이터 저장
4. 리스트에 데이터 추가
5. 데이터 삭제, 삭제된 데이터는 반환된다
6. 리스트에 저장되어 있는 데이터 수 반환
*/
/*
순차 리스트는 배열 기반으로 구현된다.
리스트 중간에 요소 도입 등은 배열의 인덱스 특성을 이용해서 구현했다.
배열의 특성은 연속된 메모리 데이터를 가진다는 것이기 때문에 순차 리스트의 메모리도 연속되어 있다.
배열을 기반으로 하기 때문에 데이터 삭제, 저장 등에서 배열이 지속적으로 복사, 생성된다는 단점이 있다.
인덱스 기반으로 이용하기 때문에 어디든 한번에 참조가 가능하다는 장점이 존재한다.
*/
public class Test {
    public static void main(String[] args) {
        ArrayListTest li = new ArrayListTest();
        String[] a = {"3","r","ir","dde","dd"};
        for (int i = 0; i < a.length; i++) {
            li.add(a[i]);
        }
        li.print();
        li.add(3,1);
        li.add('3');
        li.print();
        System.out.println(li.get(1));


        // li.get으로 가져온 객체는 Object 객체
        // 기본형 타입이 아니라 WrapperClass의 타입이기 때문에 연산이 불가하다.
        // 리스트는 모든 데이터를 담기 위해서 object 클래스로 만들어지기 때문에 이런 특성이 있다.
        // 이게 배열과의 차이점?

//        System.out.println((int)li.get(li.size()-1).+li.get(1));

    }
}

class ArrayListTest{
    // 리스트의 기반이 될 배열 (리스트 생성)
    Object[] a;
    // 리스트 길이 표기할 변수 (인덱스 역할로 활용)
    int length =0;

    // 1. 리스트 생성(주소값을 전달해야 함)
    ArrayListTest(){}

    // 리스트에 데이터 추가
    void add (Object n){
        // 맨 처음 데이터 입력일 경우, 본 리스트 메모리 생성
        if(length == 0) {
            a = new Object[1];
            a[0]=n;
            length++;
            return;
        }
        // 크기를 유동적으로 가져가기 위해 항상 배열
        // 질문 : 이렇게 배열을 복사하면 참조변수 b는 어떻게 될까?
        // 맨 끝에 데이터 입력 + 인덱스 지정 없이 데이터 입력
        else {
            Object[] b = new Object[a.length+1];
            for(int i = 0; i<a.length; i++){
                b[i] =a[i];
            }
            a=b;
            a[length]=n;
            length++;
        }
    }

    // 인덱스 지정해서 데이터 입력
    void add (Object n, int index){
        if(index<0||index>=length){
            System.out.println("IndexOutOfBounds");
            return;
        }
        else{
            Object[] b = new Object[a.length-index];
            int temp =0;
            Object[] c = new Object[a.length+1];
            // a의 index 뒤 원소들을 b 배열에 복사
            for (int i = index; i < a.length; i++) {
                b[temp] = a[i];
                temp++;
            }
            // a의 index 앞 원소들을 c 배열에 복사
            for (int i = 0; i < index; i++) {
                c[i] = a[i];
            }
            // c의 index에 n 대입
            c[index] = n;
            // c의 index 뒷 요소에 b에 복사해둔 원소 옮김
            temp =0;
            for (int i = index+1; i <c.length; i++) {
                c[i] = b[temp];
                temp ++;
            }
            // c를 a에 대입
            a = c;
            length++;
        }
    }

    // 맨 끝 데이터 삭제
    Object delete(){
        if(length==0){
            System.out.println("IndexOutOfBounds");
            return 0;
        }
        else {
            // a의 맨 끝 열 데이터를 변수에 저장
            Object c = a[a.length-1];
            // 리스트화 할 배열 길이 축소를 위해 새로운 배열 생성
            Object[] b = new Object[a.length-1];
            for (int i = 0; i < a.length-1; i++) {
                b[i] = a[i];
            }
            a=b;
            length--;
            return c;
        }
    }

    // 인덱스 지정해서 데이터 삭제
    Object delete(int index){
        if(index<0||index>=length){
            System.out.println("IndexOutOfBounds");
            return 0;
        }
        else {
            // return할 a index 값을 저장할 변수
            Object temp = a[index];
            // a index 뒷부분 요소를 저장할 배열
            Object[] b = new Object[a.length-index];
            int intemp =0;
            for (int i = index+1; i <a.length; i++) {
                b[intemp]=a[i];
                intemp++;
            }
            // a보다 크기 하나 작은 배열
            Object[] c = new Object[a.length-1];
            for (int i = 0; i < index; i++) {
                c[i] = a[i];
            }
            // c에 b의 원소들을 복사
            intemp =0;
            for (int i = index; i <c.length ; i++) {
                c[i] = b[intemp];
                intemp++;
            }
            a=c;
            length--;
            return temp;
        }
    }
    // 리스트 원소 추출
    Object get(int index){
        if(index<0||index>=length){
            System.out.println("IndexOutOfBounds");
            return 0;
        }
        else {return a[index];}
    }

    // 리스트 크기 반환
    int size(){
        return a.length;
    }

    // 리스트 전체 비우기
    void empty(){
        a = new Object[0];
        length=0;
    }

    // 리스트 내부 원소들 순차적으로 프린트하는 메소드
    void print(){
        System.out.print("[");
        for(int i =0; i<a.length; i++){
            if(i==a.length-1){
                System.out.print(a[i]);
            }
            else {
                System.out.print(a[i]+",");
            }
        }
        System.out.print("]");
        System.out.println();
    }

}
