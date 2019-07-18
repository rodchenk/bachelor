# Development of a new programming language

### Variable expression:
```ruby
var: x = 10 #number
var: str_var = "Hello World" #string
var: bol_var = false #boolean
```

### Conditional statement:
```ruby
var: cond = true
while cond:
	if cond == false and true
    		print("Deadlock")
  	else end
}
```

### Include statement:
```ruby
#bin/mir
include "array_sum"
include "print_hello"

var: array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}
var: result = get_result(array) #returns sum of all array elements

print "Array: " + array + "\t with sum: " + result
```

### Lööps:
```ruby
var: index = 0
for index end index < 10 end index = index + 1 :[
	if index == 4 continue #skip if index is 4
	if index % 2 == 0 and index != 0 print index+"\n" #all even numbers
	if index == 7 end #break for loop
]
```

### Functions:
```ruby
def sum(i, k): 
	return i + k
def diff(i, k):
	return i - k

var: temp = 100
temp = 10
print sum(temp, 34) + "\n"
print diff(120, temp) + "\n"

def get_array_sum(array):[
	var: result = 0
	for var: i = 0 end i < size_of(array) end i = i + 1:[
		result = sum(result, array[i])
	]
	return result
]
```

### Fibonacci numbers:
```ruby
def fib(n):[
	if n <= 0: 
		return n
	return fib(n-2) + fib(n-1)
]

var: _num = 10
var: result = -1 * fib(_num)

print _num + "'th Fib number is " + result; #10'th Fib number is 55
	
# or without recursive function

var: pn = 0
var: nn = 1
var: i = 1
var: sum = 0
while i <= 10:[
	print pn + "\n" #print next Fibonacci number
	sum = pn + nn
	pn = nn
	nn = sum
	i = i + 1
]
```
