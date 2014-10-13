#include <iostream>
using namespace std;

int sort(int array[]);

int main(){
	int array[10];
	int i = 0;
	for (i = 0; i < sizeof(array) / sizeof(int); i++)
	{
		array[i] = 10 - i;
		cout << array[i] << " ";
	}
	cout << endl;
	
	sort(array);

	for (i = 0; i < sizeof(array) / sizeof(int); i++)
	{
		cout << array[i] << " ";
	}
	cout << endl;
}

int sort(int array[]){
	int i = 0;
	int a = 0;

	for (i = 0; i < 9; i++)
	{
		for (a = i + 1; a < 10; a++)
		{
			if (array[i] > array[a]){
				int temp = array[i];
				array[i] = array[a];
				array[a] = temp;
			}
		}
	}
	return *array;
}