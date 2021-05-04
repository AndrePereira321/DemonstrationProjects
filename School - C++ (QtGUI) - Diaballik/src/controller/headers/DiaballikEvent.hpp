#ifndef DIABALLIKABSTRACTEVENT_H
#define DIABALLIKABSTRACTEVENT_H

namespace dblk
{

/**
 * @brief The DiaballikAbastractEvent class
 *
 * This class represents a main action launched by the user.
 * It can be a passive action that dont modifies the game data (like ask for
 * help, refresh the board...) or
 * an active action who modifies the game data (move a piece, pass turn...).
 */
class DiaballikAbstractEvent
{
  public:
    /**
     * @brief execute
     *
     * Execute the event.
     */
    virtual void execute() = 0;

    /**
     * @brief ~DiaballikEvent Destructor by default of DiaballikEvent.
     *
     */
    virtual ~DiaballikAbstractEvent() = default;
};

}

#endif // DIABALLIKEVENT_H
