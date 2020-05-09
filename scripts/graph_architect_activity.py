# -*- coding: utf-8 -*-
"""
Created on Mon May  4 20:50:24 2020

@author: TOSHIBA
"""

import sys
import numpy as np
import pandas as pd
from datetime import date, timedelta
import seaborn as sns

import matplotlib.pyplot as plt



dataset = pd.read_csv('testfile.csv')
dataset['date'] = pd.to_datetime(dataset['date'])


#ARCHITECTS COMMITS
architects_commits = dataset.iloc[:,[0,1,2,3]]
architects_commits = architects_commits.drop(['commit'],axis=1)

architects_commits = architects_commits.drop_duplicates()
architects_commits = architects_commits.drop(['file'],axis=1)
architects_commits = architects_commits.groupby(['developer','date']).size().reset_index(name='commits')



#SETDATES
'''
start_date = min(architects_commits.date)
delta = timedelta(days=1)
dates=[]
while start_date <= max(architects_commits.date):
    dates.append(start_date.strftime("%Y-%m-%d"))
    start_date += delta
    
df_date = {'date' : dates}
df_date = pd.DataFrame(df_date)
df_date['date'] = pd.to_datetime(df_date['date'])
'''

#GIVE TO EVERY DEVELOPER ALL DATE WITH 0 COMMITS
#devs =architects_commits.developer.unique()

result = pd.DataFrame(columns = ['developer','date','commits'])
result['date'] = pd.to_datetime(result['date'])
result.dtypes

result = architects_commits.iloc[:,:]

'''for i in range(0,len(devs)):
    c = pd.merge(architects_commits.loc[architects_commits.developer==devs[i]],
             df_date, on=['date'],how='outer')
    values = {'developer': devs[i], 'commits': 0}
    c.fillna(value=values,inplace=True)
    result= pd.concat([result,c])
    '''
architects_commits.sort_values(by=[ 'developer','date'],inplace =True)

for i in range(1,len(architects_commits)):
    if architects_commits.developer.iloc[i-1] == architects_commits.developer.iloc[i]:
        architects_commits.commits.iloc[i] = architects_commits.commits.iloc[i] + architects_commits.commits.iloc[i-1]

        
    
plt.figure(figsize=(20,9))
sns.lineplot(x="date", y="commits", hue="developer",  data=architects_commits,
             palette='bright')
plt.savefig('scripts/img/graph_dev_time_files.png')













