#ifndef OBSERVABLE_H
#define OBSERVABLE_H
#include <set>
#include "src/view/headers/Observer.hpp"
#include "src/controller/headers/EventType.hpp"

namespace dblk
{


/*!
 * \brief Basic class of any "subject of observation"
 *
 * Class from which any event source (or "observation subject")
 * is derived from the "Observer / SubjectDObservation" design model.
 *
 */
class Observable
{

  private:
    /*!
     * \brief The set of registered observers.
     */
    std::set<Observer *> observers_;

  public:

    /**
     * @brief Observable Constructor of a subject
     *
     */
    Observable();

    /**
     *\brief A method allowing an observer to register as a
     * earpiece of the subject of observation.
     *
     **/
    void registerObserver(Observer * observer);

    /**
    *\brief Allows to delete an observer from the list
    * the observation subject's.
    *
    * @param the address of the observer.
    **/
    void deleteObserver(Observer * observer);

    /**
     * \brief A method of notify observers of a
     * change in status of the subject of observation, invoking their
     * method Observer::update().
     *
     * \sa Observer::update(const Subject *).
     **/
    void notifyObservers(EventType type);
};

}

#endif // OBSERVABLE_H
