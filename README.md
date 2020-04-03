# PortScanner
## Java application to scout open ports of ip address

### Releases
 - [PortScanner v1.0](https://github.com/Katakurinna/PortScanner/releases/tag/1.0)
 
### How to use
You can use this release in the following way:

Adding ip minport maxport and file (if you want)

> java -jar PortScanner-1.0.jar ipOrElseDomain minPortRange maxPortRange [file-to-save-open-ports.txt]

Example:

> java -jar PortScanner-1.0.jar 127.0.0.1 0 65535 file.txt

or else

> java -jar PortScanner-1.0.jar 127.0.0.1 0 65535

Or else, adding ip and file (if you want)

> java -jar PortScanner-1.0.jar ipOrElseDomain [file-to-save-open-ports.txt]

Example:

> java -jar PortScanner-1.0.jar 127.0.0.1 file.txt

or else

> java -jar PortScanner-1.0.jar 127.0.0.1

And thanks to [@xXNurioXx](https://github.com/xXNurioXx) to lambda improve performance!
