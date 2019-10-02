# Language for those who appreciate simplicity

*mir* ist eine interpretierbare Sprache, die in Java übersetzt wird. Die Sprache wurde im Rahmen der Abschlussarbeit an der Technischen Hochschule Brandenburg erstellt.
*mir* unterstützt alles, was eine normale GPL-Sprache haben muss:
<img src="http://oregon.apwa.net/content/chapters/oregon.apwa.net/image/Chapter/Logos/MntGraphic.png" align="right" width="200">
### Variablen:

```ruby
var: my_variable = "Hello, mir"
var: my_condition = true
```

### Schleifen, Verzweigungen und bedingte Anweisungen:

```ruby
if 1 + 2 > 3:
	print "noway"

for var: index = 0 end index <= 10 end index = index * 2:[
	if index % 3: continue
	print index
]

while true:[
	print "while-loooooop"
	end
]
```

### Auch Funktionen werden unterstützt:

```ruby
def calc(a, b, oper):[
	if oper == "+": return a + b
	if oper == "-": return a - b
	if oper == "*": return a * b
	if oper == "/": return a / b
	return 0
]

def log(msg):
	print get_time() + ": " + msg
```

### Oder sogar rekursive Funktionen:

```ruby
def fib(n):[
	if n <= 0: 
		return n
	return fib(n-2) + fib(n-1)
]

var: _num = 10
var: result = -1 * fib(_num)

print _num + "'th Fib number is " + result; #10'th Fib number is 55
```

Auch andere Programmteile lassen sich einfach importieren und einfügen:

```ruby
include "lib/math"

print math_sompare(1, 10) // -1
```
