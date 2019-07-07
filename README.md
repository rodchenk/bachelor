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

### Print statement:
```ruby
print str_var + "!\n" #Hello World!
print (1 + 1 >= 1 * 2) + "\n"  #true
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
