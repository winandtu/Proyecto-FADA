#include <iostream>
#include <vector>
#include <cmath>
#include<stdio.h>
#include<cstring>
#include<iostream>
#include <fstream>


using namespace std;
int M, K;
unsigned t0, t1;
long pages[501];
bool isFinalBook[501];

bool librosCompatibles(int NumeroPaginas)
{
    long paginasActuales = 0, numeroUsado = 0;
    for (int libro = M - 1; libro >= 0; --libro)
    {
        if (pages[libro] + paginasActuales > NumeroPaginas)
        {
            ++numeroUsado;
            if (numeroUsado == K)
                return false;
            paginasActuales = pages[libro];
        }
        else
        {
            paginasActuales += pages[libro];
        }
    }
    
    return true;
}

long paginasMinimas(long sum, long largest)
{
    long min = largest, max = sum;
    
    while (min != max)
    {
        long middle = min + (max - min) / 2;
        if (librosCompatibles(middle))
            max = middle;
        
        else
            min = middle + 1;
    }
    
    return min;
}

void librosNoCompatibles(long maximumPages)
{
    long pagesForCurrent = 0, currentScribe = K - 1;
    
    
    for (int book = M - 1; book >= 0; --book)
    {
        if (pages[book] + pagesForCurrent > maximumPages ||
            currentScribe == book + 1)
        {
            --currentScribe;
            
            isFinalBook[book] = true;
            pagesForCurrent = 0;
        }
        
        pagesForCurrent += pages[book];
    }
}

int main()
{

	 	
	ofstream file;
    file.open("SalidaNormal.txt");
	ifstream fe("Prueba5.txt");
	fe >> M;
	fe >> K;	
        long sum = 0, largest = 0;
        for (int i = 0; i < M; ++i)
        {
            isFinalBook[i] = false;          
            fe >> pages[i];
            sum += pages[i];
            
            largest = max(largest, pages[i]);
        }
        
        long maxPages = paginasMinimas(sum, largest);
        cout << maxPages << endl;
        file << maxPages;
        file << "\n";
        
        librosNoCompatibles(maxPages);
 
        
        cout << pages[0];
        file << pages[0];
        if (isFinalBook[0]){
        	cout << " /";
        	file << " /";   	
		}
            
        
        for (int i = 1; i < M; ++i)
        {
        	file << " ";
        	file << pages[i];
            cout << ' ' << pages[i];
            if (isFinalBook[i]){
            	cout << " /";
            	file << " /";
            	
			}
                
        }
        file << '\n';
        cout << '\n';

	
}
