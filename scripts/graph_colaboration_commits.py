# -*- coding: utf-8 -*-
"""
Created on Mon May  4 07:22:17 2020

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



#KOJI KOMITI SU BiLI KOLAbORACIONI
multi_colab = dataset.iloc[:,[0,1,2,3]]
multi_colab["colab"] = ""
multi_colab = multi_colab.drop_duplicates()
multi_colab= multi_colab.reset_index(drop=True)



#SETDATES
max(multi_colab.date)
start_date = min(multi_colab.date)
delta = timedelta(days=1)
dates=[]
colab=[]
while start_date <= max(multi_colab.date):
    dates.append(start_date.strftime("%Y-%m-%d"))
    colab.append('t')
    dates.append(start_date.strftime("%Y-%m-%d"))
    colab.append('f')
    start_date += delta
    
df_date = {'date' : dates,'colab':colab }
df_date = pd.DataFrame(df_date)
df_date['date'] = pd.to_datetime(df_date['date'])


dev_file_dict = dict()

#ADD DEVELOPErS AS KEYS
for i in range(0,len(multi_colab.file.unique())):
    dev_file_dict[multi_colab.file.unique()[i]]=np.nan

for i in range(0,len(multi_colab)):
    #CHECK FIRST DOES IT HAVE A DEV
    if np.isnan(dev_file_dict[multi_colab.file.iloc[i]]): 
        dev_file_dict[multi_colab.file.iloc[i]]= multi_colab.developer.iloc[i]
        multi_colab.colab.iloc[i]='f'
    elif dev_file_dict[multi_colab.file.iloc[i]] == multi_colab.developer.iloc[i]:
        multi_colab.colab.iloc[i]='f'
    else:
        multi_colab.colab.iloc[i]='t'

   
multi_colab = multi_colab.drop(['developer','file','commit'],axis=1)
multi_colab = multi_colab.groupby(['date','colab']).size().reset_index(name='commits')
multi_colab.sort_values(by=['date'],inplace =True)

c = pd.merge(df_date,multi_colab, on=['date','colab'], how= 'left')
c.fillna(0,inplace=True)


plt.figure(figsize=(20,9))
sns.lineplot(x="date", y="commits", hue="colab",  data=c)
plt.savefig('scripts/img/graph_collaboration.png')

