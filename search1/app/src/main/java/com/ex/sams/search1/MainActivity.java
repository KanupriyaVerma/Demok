import android.support.v7.widget.toolbar;
private MenuItem mSearchAction;
private boolean isSearchOpened = false;
private EditText edtSeach;


@Override
public boolean onPrepareOptionsMenu(Menu menu) {
        mSearchAction = menu.findItem(R.id.action_search);
        return super.onPrepareOptionsMenu(menu);
        }
        To handle the click in the search button, edit the onOptionsItemSelected method.

@Override
public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
        case R.id.action_settings:
        return true;
        case R.id.action_search:
        handleMenuSearch();
        return true;
        }

        return super.onOptionsItemSelected(item);
        }
        The handleMenuSearch() will open and close the search EditText.

        Take a look in my handleMenuSearch, it’s commented to help you to understand.

protected void handleMenuSearch(){
        ActionBar action = getSupportActionBar(); //get the actionbar

        if(isSearchOpened){ //test if the search is open

        action.setDisplayShowCustomEnabled(false); //disable a custom view inside the actionbar
        action.setDisplayShowTitleEnabled(true); //show the title in the action bar

        //hides the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edtSeach.getWindowToken(), 0);

        //add the search icon in the action bar
        mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_open_search));

        isSearchOpened = false;
        } else { //open the search entry

        action.setDisplayShowCustomEnabled(true); //enable it to display a
        // custom view in the action bar.
        action.setCustomView(R.layout.search_bar);//add the custom view
        action.setDisplayShowTitleEnabled(false); //hide the title

        edtSeach = (EditText)action.getCustomView().findViewById(R.id.edtSearch); //the text editor

        //this is a listener to do a search when the user clicks on search button
        edtSeach.setOnEditorActionListener(new TextView.OnEditorActionListener() {
@Override
public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
        doSearch();
        return true;
        }
        return false;
        }
        });


        edtSeach.requestFocus();

        //open the keyboard focused in the edtSearch
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtSeach, InputMethodManager.SHOW_IMPLICIT);


        //add the close icon
        mSearchAction.setIcon(getResources().getDrawable(R.drawable.ic_close_search));

        isSearchOpened = true;
        }
        }
        You’d add this method too, to close the search entry with the backbutton:

@Override
public void onBackPressed() {
        if(isSearchOpened) {
        handleMenuSearch();
        return;
        }
        super.onBackPressed();
        }
        To this tutorial, I created an empty doSearch method.

        You can anything here and do your custom search:

private void doSearch() {
//
        }