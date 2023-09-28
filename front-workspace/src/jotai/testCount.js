import { atom } from 'jotai';

/**
const priceAtom = atom(10)

const readOnlyAtom = atom((get) => get(priceAtom) * 2)
const writeOnlyAtom = atom(
  null, // 첫번째 인자로 전달하는 초기값은 null로 전달
  (get, set, update) => {
    // update는 atom을 업데이트하기 위해 받아오는 값
    set(priceAtom, get(priceAtom) - update.discount)
  }
)
const readWriteAtom = atom(
  (get) => get(priceAtom) * 2,
  (get, set, newPrice) => {
    set(priceAtom, newPrice / 2)
    // set 로직은 원하는 만큼 지정할 수 있다.
  }
)
atom 내 인자로 콜백함수를 작성하면, 위 예시처럼 첫 번째는 읽기,
두 번째는 쓰기에 관련된 인자를 받는 것을 알 수 있다.
get은 다른 atom을 참조, set은 atom을 새로운 값으로 갱신, update(newPrice) 등은
갱신을 위해 입력받을 값이다.

@컴포넌트에서_사용법
import 'jotai';
const [count, setCount] = useAtom(countAtom);
const [count, ] = useAtom(countAtom);
const [, setCount] = useAtom(countAtom);
import 'jotai/utils';
const count = useAtomValue(countAtom);
const setCount = useUpdateAtom(countAtom);

@onMount
atom이 <Provider> 에서 처음으로 사용되는 시점에 호출되는 onMount() 메서드
프로퍼티
인자는 Setter 함수로 Mount 후 초기값을 재지정하고 싶을 때 사용할 수 있다.
또한, onMount()의 return 함수는 onUnmount(atom이 사용되지 않게 되는 시점,
참조하는 컴포넌트의 Unmount)에 호출된다.
// mount, unmount 기본 형태
const anAtom = atom(1)
anAtom.onMount = (setAtom) => {
  console.log('atom is mounted in provider')
  setAtom(c => c + 1) // increment count on mount
  return () => { ... } // return optional onUnmount function
}

// mount를 활용한 초기값 설정 예제
const countAtom = atom(1)
const derivedAtom = atom(
  (get) => get(countAtom),
  (get, set, action) => {
    if (action.type === 'init') {
      set(countAtom, 10)
    } else if (action.type === 'inc') {
      set(countAtom, (c) => c + 1)
    }
  }
)
derivedAtom.onMount = (setAtom) => {
  setAtom({ type: 'init' })
}

@async
atom이 동기/비동기를 모두 담당, 초기 fetch를 위해 write 함수인자를 활용하면 된다.
const fetchUrlAtom = atom(async (get) => {
  const response = await fetch(get('https://my-api.com'))
  return await response.json()
})
특히, 비동기 상태 fetch간 노출할 목적으로, <Suspense> 컴포넌트로 감싸 Fallback을
설정해줘야한다.
(통상 App 전체를 감싸나, 컴포넌트 별로 여러 개 감싸도 무방하다.)
const App = () => (
  <Provider>
    <Suspense fallback="Loading...">
      <Layout />
    </Suspense>
  </Provider>
)
*/

const countAtom = atom(0);

export default countAtom;