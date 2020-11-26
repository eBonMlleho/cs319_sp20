var rs = require('readline-sync');

var fNum1 = rs.question('1st Number: ');
var fNum2 = rs.question('2nd Number: ');
var fNum3 = rs.question('3rd Number: ');
var fNum4 = rs.question('4th Number: ');

var result1 = factorial(parseInt(fNum1));
var result2 = sumDigits(parseInt(fNum2));
var result3 = reverseString(fNum3);
var result4 = palindrome(fNum4);




console.log("Factorial of the 1st number is = "+result1);
console.log("The sum of all digits of the 2nd number is = "+result2);
console.log("The reverse of the 3rd number is = "+result3);
console.log("Is the 4th number a palindrome (true/false)? = " + result4);



function factorial(n) {
    return (n != 1) ? n * factorial(n - 1) : 1;
}

function sumDigits(value) {
    sum = 0;
  while (value) {
      sum += value % 10;
      value = Math.floor(value / 10);
  }
  return sum;
}

function reverseString(str) {
    return str.split("").reverse().join("");
}

function palindrome(str) {
    var re = /[\W_]/g;
    var lowRegStr = str.toLowerCase().replace(re, '');
    var reverseStr = lowRegStr.split('').reverse().join(''); 
    return reverseStr === lowRegStr;
  }