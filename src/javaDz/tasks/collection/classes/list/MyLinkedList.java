package javaDz.tasks.collection.classes.list;

import javaDz.tasks.collection.interfaces.ILinkedKist;

import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyLinkedList<T> implements ILinkedKist<T>
{
  private int newIndex = 0;
  private int size = 0;
  private Node<T> first;
  private Node<T> last;
  private T[] array;
  private EasyList numList = new EasyList();

  @Override
  public void add(T element)
  {
    if(first == null)
    {
      first = last = new Node<T>(element);
      numList.add(newIndex);
      newIndex++;
      size++;
    }else
    {
      Node<T> temp = new Node<T>(element);
      last.setNextNode(temp);
      temp.setPreviosNode(last);
      last = temp;
      numList.add(newIndex);
      newIndex++;
      size++;
    }
  }

  @Override
  public void add(int index,T element)
  {
    if(index >= 0 && index < newIndex)
    {
      Node<T> newEl = new Node<>(element);
      if(Math.abs(newIndex - 1 - index) < index)
      {
        Node<T> curren = last;
        Node<Integer> indexEl = numList.getLast();
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            curren.getPreviosNode().setNextNode(newEl);
            newEl.setPreviosNode(curren.getPreviosNode());
            newEl.setNextNode(curren);
            curren.setPreviosNode(newEl);
            numList.add(newIndex);
            newIndex++;
            size++;
            return;
          }//end iner if
          curren = curren.getPreviosNode();
          indexEl = indexEl.getPreviosNode();
        }
      }else
      {
        Node<T> curren = first;
        Node<Integer> indexEl = numList.getFirst();
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            curren.getPreviosNode().setNextNode(newEl);
            newEl.setPreviosNode(curren.getPreviosNode());
            newEl.setNextNode(curren);
            curren.setPreviosNode(newEl);
            numList.add(newIndex);
            newIndex++;
            size++;
            return;
          }//end iner if

          curren = curren.getNextNode();
          indexEl = indexEl.getNextNode();
        }
      }
    }//end if
  }

  @Override
  public void clear()
  {
    first = last = null;
    size = 0;
    newIndex = 0;
  }

  @Override
  public T get(int index)
  {
    if(index >= 0 && index < newIndex)
    {
      if(Math.abs(newIndex - 1 - index) < index)
      {
        Node<Integer> indexEl = numList.getLast();
        Node<T> curren = last;
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            return curren.getElement();
          }

          curren = curren.getPreviosNode();
          indexEl = indexEl.getPreviosNode();
        }//end whiel
      }else
      {
        Node<Integer> indexEl = numList.getFirst();
        Node<T> curren = first;
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            return curren.getElement();
          }

          curren = curren.getNextNode();
          indexEl = indexEl.getNextNode();
        }//end while
      }//end else in if/else
    }
    return null;
  }

  @Override
  public int indexOf(T element)
  {
    Node<Integer> indexEl = numList.getFirst();
    Node<T> curren = first;
    while(curren != null)
    {
      if(curren.getElement().equals(element))
      {
        return indexEl.getElement();
      }
      indexEl = indexEl.getNextNode();
      curren = curren.getNextNode();
    }
    return -1;
  }

  @Override
  public T remove(int index)
  {
    if(Math.abs(newIndex - 1 - index) < index)
    {
      Node<T> curren = last;
      Node<Integer> indexEl = numList.getLast();
      while(curren != null)
      {
        if(indexEl.getElement() == index)
        {
            Node<T> temp = curren;
            if(index == newIndex - 1)
            {
              curren.getPreviosNode().setNextNode(null);
              last = curren.getPreviosNode();
              numList.remove();
            }
            else
            {
              curren.getPreviosNode().setNextNode(curren.getNextNode());
              curren.getNextNode().setPreviosNode(curren.getPreviosNode());
              curren = curren.getNextNode();
              numList.remove();
            }

          newIndex--;
          size--;
          return temp.getElement();
        }

        curren = curren.getPreviosNode();
        indexEl = indexEl.getPreviosNode();
      }
    }
    else
    {
      Node<T> curren = first;
      Node<Integer> indexEl = numList.getFirst();
      while(curren != null)
      {
        if(indexEl.getElement() == index)
        {
          Node<T> temp = curren;
          if(index == 0)
          {
            curren.getNextNode().setPreviosNode(null);
            first = curren.getNextNode();
            numList.remove();
          }
          else
          {
            curren.getPreviosNode().setNextNode(curren.getNextNode());
            curren.getNextNode().setPreviosNode(curren.getPreviosNode());
            curren = curren.getNextNode();
            numList.remove();
          }

          newIndex--;
          size--;
          return temp.getElement();
        }

        curren = curren.getNextNode();
        indexEl = indexEl.getNextNode();
      }
    }
    return null;
  }

  @Override
  public T set(int index,T element)
  {
    if(index >= 0 && index < newIndex)
    {
      if(Math.abs(newIndex - 1 - index) < index)
      {
        Node<Integer> indexEl = numList.getLast();
        Node<T> curren = last;
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            T res = curren.getElement();
            curren.setElement(element);
            return res;
          }

          curren = curren.getPreviosNode();
          indexEl = indexEl.getPreviosNode();
        }//end whiel
      }else
      {
        Node<Integer> indexEl = numList.getFirst();
        Node<T> curren = first;
        while(curren != null)
        {
          if(indexEl.getElement() == index)
          {
            T res = curren.getElement();
            curren.setElement(element);
            return res;
          }

          curren = curren.getNextNode();
          indexEl = indexEl.getNextNode();
        }//end while
      }//end else in if/else
    }
    return null;
  }

  @Override
  public int size()
  {
    return size;
  }

  @Override
  public T[] toArray()
  {
    if(size > 0)
    {
      T[] resMas = (T[]) Array.newInstance(first.getElement().getClass(),size);
      Node<T> current = first;
      int iter = 0;
      while(current != null)
      {
        resMas[iter] = current.getElement();
        current = current.getNextNode();
        iter++;
      }

      return resMas;
    }
    return null;
  }

  @Override
  public Iterator<T> iterator()
  {

    return new Iterator<T>()
    {
      private Node<T> current = first;

      @Override
      public boolean hasNext()
      {
        if(current != null)
        {
          return true;
        }
        return false;
      }

      @Override
      public T next()
              throws NoSuchElementException
      {
        if(current == null)
        {
          throw new NoSuchElementException();
        }
        Node<T> temp = current;
        current = current.getNextNode();
        return temp.getElement();
      }
    };//end anonim class
  }

}
