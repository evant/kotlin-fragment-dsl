# kotlin-fragment-dsl
A nice kotlin dsl for dealing with the fragment backstack.

## Download
TODO: upload somewhere.
Note: this lib depends on `com.android.support:support-fragment:26.0.0-beta2`

## Usage
You modify the fragment backstack inside a transaction in the `fragmentStack` function in your activity or fragment.
```kotlin
fragmentStack(R.id.container) {
  pop()
  push(MyFragment())
}
```
All opertaions will be optomized with `FragmentManager#setReorderingAllowed(true)`.

### Initial Fragment
When an activity is created you usally want to ensure it has an initial fragment. You can do this with `startWith`
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
  super.onCreate(savedInstanceState)
  setContentView(R.layout.activity_main)
  fragmentStack(R.id.container) {
    startWith(InitialFragment())
  }
}
```
This will only add the fragment if no fragments yet exist in the container.

### Push Options
When pushing a fragment (or with the starting the fragment) you can specify tranaction options. This is usually animations.
```kotlin
fragmentStack(R.id.container) {
  push(MyFragment()) {
    enter = android.R.animator.enter
    exit = android.R.animator.exit
    popEnter = android.R.animator.pop_enter
    popExit = android.R.animator.pop_exit
    
    transition = TRANSIT_FRAGMENT_OPEN
    
    transityStyle = R.style.myStyle
    
    sharedElements["name"] = sharedView
  }
}
```

### Pop Options

- `pop()` will pop the top pushed fragment, or do nothing if there isn't anything to pop. The initial fragment will never be popped.
- `popAll()` will pop all fragments (except the initial fragment).
- `popTo(backStackName, inclusive = false)` will pop to the given back stack name. This is provided when pushing a fragment with
`push(MyFragment(), backStackName = "foo")`. There is an optional `inclusive` flag, the default is non-inclusive.
