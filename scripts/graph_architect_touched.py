# -*- coding: utf-8 -*-
"""
Created on Tue May  5 15:44:10 2020

@author: TOSHIBA
"""

import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
import seaborn as sns
from datetime import date, timedelta


# Importing the dataset
dataset = pd.read_csv('testfile.csv')
dataset['date'] = pd.to_datetime(dataset['date'])


#FILTRIRAJ LICNE FAJLOVE
multi_colab = dataset.iloc[:,[0,1,2,3]]
#multi_colab["colab"] = ""
multi_colab = multi_colab.drop_duplicates()
#multi_colab= multi_colab.reset_index(drop=True)


#SETDATES
max(multi_colab.date)
start_date = min(multi_colab.date)
delta = timedelta(days=1)
dates=[]
while start_date <= max(multi_colab.date):
    dates.append(start_date.strftime("%Y-%m-%d"))
    start_date += delta
    
df_date = {'date' : dates }
df_date = pd.DataFrame(df_date)
df_date['date'] = pd.to_datetime(df_date['date'])

#ADD DATES TO COLLAB DF

c = pd.merge(df_date,multi_colab, on=['date'], how= 'left')

c=multi_colab.iloc[:,:]
c['date'] = pd.to_datetime(c['date'])


c['colab']=''
c.sort_values(by=['date'],inplace =True)
c.fillna(0,inplace=True)
c.file = c.file.astype(int)

c.dtypes


#SET IF FALUES IS MUTLTICOLABORATEd
file_colab = dict()
#ADD INIT WITH FALSE AS KEYS
for i in range(0,len(multi_colab.file.unique())):
    file_colab[multi_colab.file.unique()[i]]=-1
file_colab[0]=0
    
#SET COLAB FILES AND COMMITS
for i in range(0,len(c)):
    
    if file_colab[c.file.iloc[i]] == -1:
        c.colab.iloc[i]='f'
        file_colab[c.file.iloc[i]] = c.developer.iloc[i]
    elif file_colab[c.file.iloc[i]] == c.developer.iloc[i]:
        c.colab.iloc[i]='f'
    else:
        c.colab.iloc[i]='t'

c.loc[c.developer == 0,'colab']='x'
        
c = c.drop(['developer','file','commit'],axis=1)
c = c.groupby(['date','colab']).size().reset_index(name='files')

plt.figure(figsize=(20,9))
sns.lineplot(x="date", y="files", hue="colab",  data=c)
plt.savefig('scripts/img/graph_collaboration.png')



        
    





























