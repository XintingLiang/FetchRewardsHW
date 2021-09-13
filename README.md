# Fetch Rewards Take Home
For this project, the Android App fetches Json from a static URL, saves it to a Room database, and displays it in a RecyclerView.
<img src="https://github.com/XintingLiang/FetchRewardsHW/blob/main/screenshot-0.png" width="240" /> <img src="https://github.com/XintingLiang/FetchRewardsHW/blob/main/screenshot-1.png" width="240" />

# Libraries
Libraries used for this project include: 
- Groupie (used for RecyclerView's Adapter)
- Retrofit (used to make network requests)
- Moshi (used to convert Json response to data object)
- Room (used to store data locally)

# Notes
At first, I used Kotlin to filter the data based on name and listId;
```
response.body()?
  .filter { !it.name.isNullOrBlank() }                  // filters out rows where name is null or blank
  .sortedBy { it.name!!.replace("Item ", "").toInt() }  // sort data by item (numeric value); removes the string "Item " and then convert toInt()
  .sortedBy { it.listId }                               // finally, sort by listId
```
Later I decided to create a Room database to store all of the original data, and then filter the data through Dao with queries.
In the future, the client would not need to fetch with a network request since it will be stored in the database locally. But, if the data were to change then only on app open would we fetch again to update the data in the Room database. Also, older data will not get removed if it is not present in the response with the current implementation.
