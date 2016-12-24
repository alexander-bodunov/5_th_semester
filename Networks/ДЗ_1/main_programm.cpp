#include <iomanip>
#include "cycle_algorithm.h"

using namespace std;


void main()
{	
	setlocale(LC_ALL, "Russian");
	string number = "00001010001";
	cout << number << '\n';
	bit str = get_bit_str(number);
	cout <<"  " << str << '\n';
	bit mistake = bit("0");
	for (int i2 = 14; i2 >= 0; i2--)
	{
		mistake.set(i2);
		cout << "Ошибка:           " << mistake << '\n';
		bit str2 = Xor(str, mistake);
		cout << "Код с ошибкой:    " << str2 << '\n';
		bit answer = decode(str2);
		cout << "Исправленный код: " << answer << '\n' << '\n';
		mistake.reset(i2);
	}
	int stat[16], stat2[16];
	for (int q = 0; q < 16; q++)
	{
		stat[q] = 0;
		stat2[q] = 0;
	}
	for (int i = 1; i < 32768; i++)
	{
		bit bstr = bit(_ULonglong(i));
		int k = i;
		int n = num_ones(bstr);
		stat2[n]++;
		bit bstr2 = Xor(str, bstr);
		bstr2 = decode(bstr2);
		if (bstr2 == str)
			stat[n]++;
	}
	int sum = 0, sum2 = 0;
	cout << " Кратность ошибки| исправление ошибок| % исправления ошибок: \n";
	for (int w = 1; w < 16; w++)
	{
		cout << setw(15) << " " << setw(2) << w << " | " << setw(4) << stat[w] << " out of " << setw(4) << stat2[w] << " | " << double(stat[w]) * 100 / double(stat2[w]) << "%\n";
		sum += stat[w];
		sum2 += stat2[w];
	}
	cout << "\nИтог: " << sum << " исправлено ошибок из " << sum2 << ". " << double(sum) * 100 / double(sum2) << " %\n";
	system("Pause");
}