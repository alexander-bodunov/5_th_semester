#include <bitset>
#include <string.h>
#include <math.h>
#include <iostream>
#define bit bitset<15>
#define divider bit("000000000010011")
using namespace std;

//Функция сдвига на любое количество в сторону, которую зададут
bit shift(bit num,int step, bool side = false)
{
	for (int i = 0; i < step; i++)
	{
		if (side == true)
		{
			bool b = num.test(0);
			num >>= 1;
			num.set(14, b);
		}
		if (side == false)
		{
			bool b = num.test(14);
			num <<= 1;
			num.set(0, b);
		}
	}
	return num;
}
//Функция XOR
bit Xor(bit str1, bit str2)
{
	return str1^str2;
}
//Функция деления методом исключающего ИЛИ
bit bit_xordivide(bit bit1, bit bit2)
{
	while (bit2.test(14) != 1)
		bit2 = shift(bit2, 1);
	int q = 14;
	while (q > 4)
	{
		while (bit1.test(q) != 1)
		{
			bit2 = shift(bit2, 1, true);
			q--;
			if (q == 0) break;
		}
		if ((q <= 4) && (!bit1.test(4))) break;
		bit1 = Xor(bit1, bit2);
	}
	return bit1;
}
//Функция кодирования входящего сигнала
bit get_bit_str(string input)
{
	bit bit_input = bit(input);
	bit_input = shift(bit_input, 4);
	bit remains = bit_xordivide(bit_input, divider);
	bit bit_output = Xor(bit_input, remains);
	return bit_output;
}
//Функция исправления одной ошибки
bit decode(bit bitstr)
{
	 int sen;
	 bit synd = bit_xordivide(bitstr, divider);
	 int q = 14;
	 while (synd.to_ulong() != 0)
	 {
		 if (synd.to_ulong() == 9)
		 {
			 sen = synd.to_ulong();
			 bitstr.flip(q);
			 synd = synd.reset();

		 }
		 else
		 {
			 synd = shift(synd, 1);
			 synd = bit_xordivide(synd, divider);
			 q--;
		 }
	 }
	 return bitstr;
}
//Функция проверки на наличие ошибки
bool check(bit bitstr)
{
	bit synd = bit_xordivide(bitstr, divider);
	if (synd.to_ulong() == 0)
		return true;
	else return false;
}
//Функция подсчета количества единиц в коде
int num_ones(bit input)
{
	int n = 0;
	for (int i = 0; i < 15; i++)
		if (input.test(i) == 1)
			n++;
	return n;
}

