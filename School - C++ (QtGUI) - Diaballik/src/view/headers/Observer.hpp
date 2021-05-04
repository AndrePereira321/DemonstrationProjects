#ifndef OBSERVER_HPP
#define OBSERVER_HPP

#include "src/controller/headers/EventType.hpp"
namespace dblk
{

class Observable;


/**
 * @brief Basic abstract class of any observer.
 *
 * Class from which any listener (or "observer") derives from the
 * "Observer / SubjectObservation" design.
 *
 **/
class Observer
{
  public:


    /**
     * @brief Constuctor by default copy.
     * The default virtual destructor has cascading effects.
     *
     **/
    Observer(const Observer &) = default;

    /**
     * @brief Constuctor by deplacement by default.
     *
     **/
    Observer(Observer &&) = default;

    /**
     * @brief Operateur of assignation by recopy by default
     *
     **/
    Observer & operator=(const Observer &) = default;

    /**
     * @brief Operateur of assignation by deplacement by default
     *
     **/
    Observer & operator=(Observer &&) = default;


    /**
     * @brief Pure virtual method that each concrete observer must
     * implement: this method is called by the subject observed during
     * a notification.
     *
     *  param subject the subject of observation which notifies a change.
     *  see Subject :: notifyObservers ().
     **/
    virtual void update(const Observable * obj, EventType type) = 0;


    /**
     *  @brief Virtual destructor by default because of polymorphic use.
     **/
    virtual ~Observer() = default;

  protected:

    /**
     * @brief Protected constructor to avoid instantiation excluding inheritance.
     *
     **/
    Observer() = default;
};

} // End namespace dblk

#endif // OBSERVER_HPP
