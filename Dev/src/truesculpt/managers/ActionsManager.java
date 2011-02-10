package truesculpt.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import truesculpt.actions.BaseAction;
import truesculpt.actions.ColorizeAction;
import truesculpt.actions.InitialCreationAction;
import truesculpt.actions.SculptAction;
import android.content.Context;

//For undo redo and analytical description of sculpture
public class ActionsManager extends BaseManager
{
	private List<BaseAction> mUndoActionsList = new ArrayList<BaseAction>();
	private List<BaseAction> mRedoActionsList = new ArrayList<BaseAction>();
	
	public ActionsManager(Context baseContext)
	{
		super(baseContext);
	}

	
	@Override
	public void onCreate()
	{

	}
	
	public void Remove(int position)
	{
		mUndoActionsList.remove(position);
		NotifyListeners();
	}
	
	public void RemoveUpTo(int position)
	{
		if (position < GetUndoActionCount())
		{
			for (int i=0;i<=position;i++)
			{
			  mUndoActionsList.remove(0);
			}
		}
		NotifyListeners();
	}

	@Override
	public void onDestroy()
	{

	}

	public int GetUndoActionCount()
	{
		return mUndoActionsList.size();
	}
	
	public int GetRedoActionCount()
	{
		return mRedoActionsList.size();
	}

	public BaseAction GetUndoActionAt(int position)
	{
		BaseAction res=null;
	
		if (position<GetUndoActionCount())
		{
			res= mUndoActionsList.get(position);
		}
		
		return res;
	}
	
	public void AddUndoAction(BaseAction action)
	{
		action.setManagers(getManagers());
		mUndoActionsList.add(0,action);//add at the top
		mRedoActionsList.clear();//new branch no more needed
		NotifyListeners();
	}

	public void Redo()
	{
		if (GetRedoActionCount()>0)
		{
			BaseAction action=mRedoActionsList.get(0);
			action.DoAction();
			mRedoActionsList.remove(0);
			mUndoActionsList.add(0,action);//add at the top			
			NotifyListeners();
		}
	}

	public void Undo()
	{
		if (GetUndoActionCount()>0)
		{
			BaseAction action=mUndoActionsList.get(0);
			action.UndoAction();
			mUndoActionsList.remove(0);
			mRedoActionsList.add(0,action);
			NotifyListeners();
		}
	}
}
