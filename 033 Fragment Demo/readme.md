# Fragment transaction 
            Fragment fragment = new FragmentDemo();

            FragmentManager manager = getFragmentManager();
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragmentId, fragment);
            transaction.commit();
