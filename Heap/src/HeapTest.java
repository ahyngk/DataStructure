import java.util.ArrayList;

public class HeapTest {
    public static void main(String[] args) {
        heap test = new heap();
        test.heapAddMin(1);
        test.heapAddMin(10);
        test.heapAddMin(5);
        test.heapAddMin(2);
        test.heapAddMin(1);
        test.print();
        test.heapDeleteMin();
        test.print();
        test.heapDeleteMin();
        test.print();
    }
}

// 배열을 통해서 구현한다
class heap{
    // 몸통이 될 배열 생성
    // 인덱스가 0부터 시작할 경우, 이진트리 자식에서 부모 인덱스 구하는 식이 안떨어진다
    // 1번 인덱스 부모가 3,4번 자식을 가지고 있기 때문
    // 그래서 맨 처음 더미 노드를 하나 두어 인덱스를 맞춰준다

    // 초기 배열리스트 선언은 클래스 안에서 진행해 모든 클래스 내에서 사용 가능하도록 함
    // add 메소드 같은 경우 생성자 내부가 아닌 곳에서 작성할 경우, 객체 생성이 확실하지 않기 때문에 작성 안됨
    ArrayList <Integer> heapBase = new ArrayList<>();
    heap(){
        heapBase.add(0);
    }


    // 최소 힙의 경우 데이터 삽입 메소드
    void heapAddMin(int data){
        //일단 맨 끝에 데이터 삽입, 인덱스 값 저장
        // 인덱스의 값을 부모 인덱스의 값과 비교해가며 계산
        // 밑에 딸린 트리들을 옮기는 개념은 이진트리를 깨기 때문에 부모-자식간의 작은 자리바꿈을 계속 하면서 올라가야 한다
        heapBase.add(data);
        int dataIndex = heapBase.size()-1;
        // 트리의 처음 값을 입력했을 때는 그냥 받아준다
        if(dataIndex==1){
            return;
        }
        // 이후 입력된 값들은 부모외 비교해 판별하는 과정 가짐
        while (dataIndex>1){
            if(addCheckMin(dataIndex,dataIndex/2)){
                dataIndex = dataIndex/2;
            }
            else {
                return;
            }
        }
    }
    int heapDeleteMin(){
        // 반환할 데이터 킵하기
        int toreturn = heapBase.get(1);
        // 맨 위의 노드 지우고, 맨 뒤의 노드를 맨 위로 올리기
        heapBase.set(1,heapBase.get(heapBase.size()-1));
        heapBase.remove(heapBase.size()-1);
        // 옮길 대상 노드의 인덱스 설정
        // 자식 노드 둘 중 작은 값을 구해 인덱스 저장
        int index =1;
        int liIndex = (heapBase.get(index*2)<heapBase.get(index*2+1))?index*2:(index*2+1);
        // 자식노드가 있고, 자식노드의 값이 부모노드보다 작아 교환이 필요할 경우에 반복
        while (liIndex<heapBase.size()&&heapBase.get(liIndex)<heapBase.get(index)){
            // 자식노드와 부모노드 값 변경
            int temp = heapBase.get(index);
            heapBase.set(index,heapBase.get(liIndex));
            heapBase.set(liIndex,temp);
            // 자식노드가 있는지 여부 먼저 확인, 그 여부에 따라 다시 비교할 부모노드 인덱스, 자식노드 인덱스 설정
            // leaf 노드 확인 빼먹지 않기
            index =liIndex;
            if(liIndex*2>=heapBase.size()){
                break;
            }
            else if(liIndex*2+1>=heapBase.size()){
                liIndex *=2;
            }
            else {
                liIndex = (heapBase.get(index * 2) < heapBase.get(index * 2 + 1)) ? index * 2 : (index * 2 + 1);
            }
        }
        return toreturn;
    }

    // 최소힙의 경우, 자식 노드의 값을 부모 노드의 값과 비교해서 자리바꿈 해주는 메소드
    // ArrayList의 'set'메소드를 사용하면 그 자리에 있는 데이터를 바꿀 수 있다 (지우고 추가하는 과정 없이)
    boolean addCheckMin(int dataIndex, int parentIndex){
        if(heapBase.get(dataIndex)<heapBase.get(parentIndex)){
            int parentTemp = heapBase.get(parentIndex);
            int dataTemp = heapBase.get(dataIndex);
            heapBase.remove(dataIndex);
            heapBase.remove(parentIndex);
            heapBase.add(parentIndex,dataTemp);
            heapBase.add(dataIndex,parentTemp);
            return true;
        }
        return false;
    }

    // 힙 프린트 해주는 메소드, ArrayList는 println에서 출력 정의되어 있어, 그것 바로 이용함
    void print(){
        System.out.println(heapBase);
    }
}