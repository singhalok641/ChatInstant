package com.example.root.chatinstant.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.view.View.OnClickListener;

import com.example.root.chatinstant.ChatAdapter;
import com.example.root.chatinstant.ChatMessage;
import com.example.root.chatinstant.CommonMethods;
import com.example.root.chatinstant.MainActivity;
import com.example.root.chatinstant.R;

import java.util.ArrayList;
import java.util.Random;
import butterknife.ButterKnife;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by root on 3/30/17.
 */

public class Chats extends Fragment implements OnClickListener {

    @BindView(R.id.messageEditText) EditText mMsgEditText;
    @BindView(R.id.msgListView)
    RecyclerView mMsgRecyclerView;
    public static ChatAdapter mChatAdapter;


    private String user1 = "singhalok641", user2 = "akmalfaraz";
    private Random random;
    public static ArrayList<ChatMessage> chatlist;
    private LinearLayoutManager layoutManager;

    public Chats() {
    }


    public static Chats newInstance() {
        Chats fragment = new Chats();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this,view);

        random = new Random();
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(
                "Chats");


        chatlist = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setStackFromEnd(true); // layoutManager.setReverseLayout(true);
        mMsgRecyclerView.setLayoutManager(layoutManager);
        mChatAdapter = new ChatAdapter(getActivity(),chatlist);
        mMsgRecyclerView.setAdapter(mChatAdapter);


        return view;
    }

    public void sendTextMessage(View v) {
        String message = mMsgEditText.getEditableText().toString();
        if (!message.equalsIgnoreCase("")) {
            final ChatMessage chatMessage = new ChatMessage(user1, user2,
                    message, "" + random.nextInt(1000), true);
            chatMessage.setMsgID();
            chatMessage.body = message;
            chatMessage.Date = CommonMethods.getCurrentDate();
            chatMessage.Time = CommonMethods.getCurrentTime();
            mMsgEditText.setText("");
            mChatAdapter.add(chatMessage);
            mChatAdapter.notifyDataSetChanged();
            mMsgRecyclerView.smoothScrollToPosition(mChatAdapter.getItemCount());

            MainActivity activity = ((MainActivity) getActivity());
            activity.getmService().xmpp.sendMessage(chatMessage);
        }
    }

    @OnClick(R.id.sendMessageButton)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendMessageButton:
                sendTextMessage(v);
                break;
            default:
                break;
        }
    }



  /*  // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }*/

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       /* if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //mListener = null;
    }


    /*public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }*/
}
