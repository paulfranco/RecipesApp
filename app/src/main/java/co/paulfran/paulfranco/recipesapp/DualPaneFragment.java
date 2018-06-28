package co.paulfran.paulfranco.recipesapp;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class DualPaneFragment extends Fragment {
    private static final String INGREDIENTS_FRAGMENT = "Igredients Fragment";
    private static final String DIRECTIONS_FRAGMENT = "Directions Fragment";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        int index = getArguments().getInt(ViewPagerFragment.KEY_RECIPE_INDEX);
        //Toast.makeText(getActivity(), Recipes.names[index], Toast.LENGTH_SHORT).show();
        getActivity().setTitle(Recipes.names[index]);
        View view = inflater.inflate(R.layout.fragment_dualpane, container, false);

        FragmentManager fragmentManager = getChildFragmentManager();

        DirectionsFragment savedDirectionsFragment = (DirectionsFragment) fragmentManager.findFragmentByTag(DIRECTIONS_FRAGMENT);
        if (savedDirectionsFragment == null) {
            DirectionsFragment directionsFragment = new DirectionsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            directionsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.rightPlaceholder, directionsFragment, DIRECTIONS_FRAGMENT).commit();
        }

        IngredientsFragment savedIngredientsFragment = (IngredientsFragment) fragmentManager.findFragmentByTag(INGREDIENTS_FRAGMENT);
        if (savedIngredientsFragment == null) {
            IngredientsFragment ingredientsFragment = new IngredientsFragment();
            Bundle bundle = new Bundle();
            bundle.putInt(ViewPagerFragment.KEY_RECIPE_INDEX, index);
            ingredientsFragment.setArguments(bundle);
            fragmentManager.beginTransaction().add(R.id.leftPlaceholder, ingredientsFragment, INGREDIENTS_FRAGMENT).commit();
        }

        return view;
    }

    @Override
    public void onStop() {
        super.onStop();
        getActivity().setTitle(getResources().getString(R.string.app_name));
    }
}
