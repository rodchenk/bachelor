# Development of a new programming language

### Variable expression:
```ruby
x :number = 10
str_var :string = "Hello World"
bol_var :boolean = false
```

### Conditional statement:
```ruby
cond :boolean = true
while cond:
	if cond == false and true
    		print("Deadlock")
  	else end
}
```

### Print statement:
```ruby
print(str_var + "!\n"); #Hello World!
print((1 + 1 >= 1 * 2) + "\n"); #true
```

### Lööps:
```ruby
for index :number = 0; index < 10; index = index + 1 :[
	if index == 4 continue #skip if 4
	if index % 2 == 0 and index != 0 print index+"\n" #all even numbers except 4
	if index == 7 end #break for loop
]
```

### Fibonacci numbers:
```ruby
pn :number = 0
nn :number = 1
i :number = 1
sum :number = 0
while i <= 10:[
	print pn + "\n" #print next Fibonacci number
	sum = pn + nn
	pn = nn
	nn = sum
	i = i + 1
]
```
