Work in progress

### toKotlin() like toString() but returns kotlin code as String ###

Example:
```kotlin
arrayOf(1,2,3).toKotlin() == "arrayOf(1, 2, 3)"
arrayOf<Int>().toKotlin() == "arrayOf<Int>()"
```

Purpose of this is to create test doubles easily.
Uses generics reflection with `reified`. Is extension function. Meant as development tool.


#### Implemented ####
Arrays, Lists, Maps, Sets

#### TODO ####
 - Data classes
 - Functions
 - Lambdas ( probably not possible without parsing all codebase )