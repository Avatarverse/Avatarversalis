package net.avatarverse.avatarversalis.core.board;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import net.avatarverse.avatarversalis.Avatarversalis;
import net.avatarverse.avatarversalis.core.user.AvatarPlayer;

/**
 * Manager for every instance of {@link BendingBoard}
 */

public final class BendingBoardManager {

    private final Map<AvatarPlayer, BendingBoard> playerBoards; //Maybe map UUIDS with the scoreboards instead?
    private final boolean enabled = true; //Get this value from config later

    public BendingBoardManager() {
        playerBoards = new ConcurrentHashMap<>(); //Multithread safe!
    }

}
