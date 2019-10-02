# Language for those who appreciate simplicity

*mir* ist eine interpretierbare Sprache, die in Java übersetzt wird. Die Sprache wurde im Rahmen der Abschlussarbeit an der Technischen Hochschule Brandenburg erstellt.
*mir* unterstützt alles, was eine normale GPL-Sprache haben muss:
<img src="http://oregon.apwa.net/content/chapters/oregon.apwa.net/image/Chapter/Logos/MntGraphic.png" align="right" width="200">
### Variablen:

```ruby
var: my_string = "Hello, mir"
var: my_boolean = true
var: my_number = -1.25
var: my_array = [my_string, my_boolean, my_number]
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
print _num + "'th Fib number is " + fib(_num); #10'th Fib number is 55
```

Auch andere Programmteile lassen sich einfach importieren und einfügen:

```ruby
include "lib/math"

print math_sompare(1, 10) // -1
```

Die Standardfunktionen:

`get_time():number` liefert aktuelle Unixzeit zurück:
```ruby
def get_time()
```

Funktion `size_of(string | array):number` gibt die Länge eines Zeichenkettes zurück, als Parameter erwartet sie `string` oder `array`.
Funktion `str_to_num` kann ein String zu Nummer umwandeln. Wenn es nicht klappt, wird die Funktion eine Fehlermeldung auslösen:

```ruby
def str_to_num(string):number
```

Ein String kann man mit `split` Methode zerlegen. Dabei bekommt Sie zwei Params: eine zu zerlegendes String und ein Pattern: 

```ruby
def split(target:string, muster:string):array
```
Wenn der erste Parameter nicht vom Typ String ist, wird die Methode versuchen, diesen Parameter zu String zu kasten und es zurückliefern.
